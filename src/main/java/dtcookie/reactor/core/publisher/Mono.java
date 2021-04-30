/**
* @license
* Copyright 2020 Dynatrace LLC
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
 */
package dtcookie.reactor.core.publisher;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.Executor;

import reactor.core.CoreSubscriber;

public final class Mono<T> extends reactor.core.publisher.Mono<T> implements Executor, Runnable {
	
	public static <X> reactor.core.publisher.Mono<X> decorate(reactor.core.publisher.Mono<X> mono) {
		if (mono == null) {
			return null;
		}
		if (mono instanceof Mono) {
			return mono;
		}
		return new Mono<X>(mono);
	}
	
	private final reactor.core.publisher.Mono<T> mono;
	private CoreSubscriber<? super T> actual;
	
	private Mono(reactor.core.publisher.Mono<T> mono) {
		this.mono = mono;
		execute(this);
	}

	@Override
	public void subscribe(CoreSubscriber<? super T> actual) {
		this.actual = actual;
		run();
	}

	@Override
	public void run() {
		mono.subscribe(actual);		
	}

	@Override
	public T block() {
		return mono.block();
	}

	@Override
	public T block(Duration timeout) {
		return mono.block(timeout);
	}

	@Override
	public Optional<T> blockOptional() {
		return mono.blockOptional();
	}

	@Override
	public Optional<T> blockOptional(Duration timeout) {
		return mono.blockOptional(timeout);
	}

	@Override
	public String toString() {
		return mono.toString();
	}

	@Override
	public void execute(Runnable command) {	
	}

}
