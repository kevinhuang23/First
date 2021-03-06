package Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {
    private final Sync sync = new Sync(3);

    private static final class Sync extends AbstractQueuedSynchronizer {

        Sync(int count) {
            if(count < 0) {
                throw new IllegalArgumentException("count must large than zero");
            }
            setState(count);
        }

        @Override
        public int tryAcquireShared(int reducecount) {
            for (;;) {
                int current = getState();
                int newCount = current - reducecount;
                if (newCount < 0 || compareAndSetState(current,newCount)) {
                    return newCount;
                }
            }
        }

        @Override
        protected boolean tryReleaseShared(int returnCount) {
            for(;;) {
                int current = getState();
                int newCount = current + returnCount;
                if (compareAndSetState(current,newCount)) {
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }
    //其他接口略

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null;
	}
}