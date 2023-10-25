public class MathRunnable implements Runnable {
    int i_;
    int maxValue_;
    int numThreads_;

    long answer ;

    MathRunnable(int i, int maxValue, int numThreads) {
        i_ = i;
        maxValue_ = maxValue;
        numThreads_ = numThreads;
    }

    @Override
    public void run() {
        for (int j = (i_ * maxValue_ / numThreads_); j < Math.min((i_ + 1) * maxValue_ / numThreads_, maxValue_); j++) {
            answer += j;
        }

    }
}
