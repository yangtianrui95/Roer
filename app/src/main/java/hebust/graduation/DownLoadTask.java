package hebust.graduation;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class DownLoadTask extends Thread {
    private String downloadUrl;// 下载链接地址
    private int threadNum;// 开启的线程数
    private String filePath;// 保存文件路径地址
    private int blockSize;// 每一个线程的下载量

    private OnCompleteCallBack mCompleteCallBack;

    private boolean mIsError;

    public interface OnCompleteCallBack{
        void onComplete(String filePath, boolean isError);
    }


    public DownLoadTask(String downloadUrl, int threadNum, String fileptah) {
        this.downloadUrl = downloadUrl;
        this.threadNum = threadNum;
        this.filePath = fileptah;
    }

    @Override
    public void run() {

        FileDownloadThread[] threads = new FileDownloadThread[threadNum];
        try {
            URL url = new URL(downloadUrl);
            URLConnection conn = url.openConnection();
            // 读取下载文件总大小
            int fileSize = conn.getContentLength();
            if (fileSize <= 0) {
                System.out.println("读取文件失败");
                return;
            }

            // 计算每条线程下载的数据长度
            blockSize = (fileSize % threadNum) == 0 ? fileSize / threadNum
                    : fileSize / threadNum + 1;


            File file = new File(filePath);
            for (int i = 0; i < threads.length; i++) {
                // 启动线程，分别下载每个线程需要下载的部分
                threads[i] = new FileDownloadThread(url, file, blockSize,
                        (i + 1));
                threads[i].setName("Thread:" + i);
                threads[i].start();
            }

            boolean isfinished = false;
            // 自旋等待
            while (!isfinished) {
                isfinished = true;
                // 当前所有线程下载总量
                for (FileDownloadThread thread : threads) {
                    if (!thread.isCompleted()) {
                        isfinished = false;
                    }
                    mIsError |= thread.mIsError;
                }
            }
            if (mCompleteCallBack != null){
                mCompleteCallBack.onComplete(filePath, mIsError);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private class FileDownloadThread extends Thread {

        private final String TAG = FileDownloadThread.class.getSimpleName();

        private boolean isCompleted = false;
        private int downloadLength = 0;
        private File file;
        private URL downloadUrl;
        private int threadId;
        private int blockSize;

        private boolean mIsError;

        public FileDownloadThread(URL downloadUrl, File file, int blocksize,
                                  int threadId) {
            this.downloadUrl = downloadUrl;
            this.file = file;
            this.threadId = threadId;
            this.blockSize = blocksize;
        }

        @Override
        public void run() {

            BufferedInputStream bis = null;
            RandomAccessFile raf = null;

            try {
                URLConnection conn = downloadUrl.openConnection();
                conn.setAllowUserInteraction(true);

                int startPos = blockSize * (threadId - 1);
                int endPos = blockSize * threadId - 1;

                conn.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
                System.out.println(Thread.currentThread().getName() + "  bytes="
                        + startPos + "-" + endPos);
                logHeaders(conn.getRequestProperties(), true);
                logHeaders(conn.getHeaderFields(), false);
                byte[] buffer = new byte[1024];
                bis = new BufferedInputStream(conn.getInputStream());

                raf = new RandomAccessFile(file, "rwd");
                raf.seek(startPos);
                int len;
                while ((len = bis.read(buffer, 0, 1024)) != -1) {
                    raf.write(buffer, 0, len);
                    downloadLength += len;
                }
                isCompleted = true;
                Log.d(TAG, "current thread task has finished,all size:"
                        + downloadLength);

            } catch (IOException e) {
                mIsError = true;
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (raf != null) {
                    try {
                        raf.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public boolean isCompleted() {
            return isCompleted;
        }

        public int getDownloadLength() {
            return downloadLength;
        }


        private void logHeaders(Map<String, List<String>> headers, boolean isRequest) {
            if (headers == null) {
                return;
            }
            L.setTAG("ytr");
            L.d(headers.toString());
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, List<String>> e : headers.entrySet()) {
                if (e.getKey() != null) {
                    sb.append(e.getKey()).append(" : ");
                }
                if (e.getValue() != null) {
                    sb.append(e.getValue()).append("\n");
                }
            }
            if (isRequest) {
                FileLoadActivity.requestHeader.add(sb.toString());
            } else {
                FileLoadActivity.responseHeader.add(sb.toString());
            }
        }

    }

    public void setOnCompleteCallBack(OnCompleteCallBack completeCallBack) {
        mCompleteCallBack = completeCallBack;
    }
}

