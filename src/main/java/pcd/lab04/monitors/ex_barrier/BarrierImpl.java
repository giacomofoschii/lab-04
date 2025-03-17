package pcd.lab04.monitors.ex_barrier;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Barrier - to be implemented
 */
public class BarrierImpl implements Barrier {
	private final int nParticipants;
	private int count = 0;
	private final Lock lock = new ReentrantLock();
	private final Condition allArrived = lock.newCondition();
	
	public BarrierImpl(int nParticipants) {
		this.nParticipants = nParticipants;
	}
	
	@Override
	public void hitAndWaitAll() throws InterruptedException {
		lock.lock();
		try {
			count++;
			if(count<nParticipants) {
				allArrived.await();
			} else {
				allArrived.signalAll();
			}
		} finally {
			lock.unlock();
		}
	}
}
