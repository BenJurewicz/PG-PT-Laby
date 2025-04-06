package jkz.lab4.Helpers;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

// TODO: Remove if found useless
public class TerminateOnUserInput implements Runnable {
	private static AtomicBoolean stopSignal;
	private static AtomicBoolean forceStopSignal;

	public TerminateOnUserInput(AtomicBoolean stopSignal, AtomicBoolean forceStopSignal) {
		TerminateOnUserInput.stopSignal = stopSignal;
		TerminateOnUserInput.forceStopSignal = forceStopSignal;
	}

	@Override
	public void run() {
		try {
			while (System.in.available() == 0 && !stopSignal.get()) {
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		if (!stopSignal.get()) {
			forceStopSignal.set(true);
		}
	}
}
