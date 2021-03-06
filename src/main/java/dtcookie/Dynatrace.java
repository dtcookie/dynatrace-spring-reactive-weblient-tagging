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
package dtcookie;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public final class Dynatrace {
	
	private Dynatrace() {
		// prevent instantiation
	}
	
	public static <X> Mono<X> decorate(Mono<X> mono) {
		return dtcookie.reactor.core.publisher.Mono.decorate(mono);
	}
	
	public static WebClient decorate(WebClient client) {
		return dtcookie.org.springframework.web.reactive.function.client.WebClient.decorate(client);
	}	

}
