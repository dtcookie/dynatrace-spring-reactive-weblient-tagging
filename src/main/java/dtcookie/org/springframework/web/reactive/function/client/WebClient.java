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
package dtcookie.org.springframework.web.reactive.function.client;

import java.net.URI;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

import org.reactivestreams.Publisher;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.http.client.reactive.ClientHttpResponse;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.util.UriBuilder;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;

public class WebClient implements org.springframework.web.reactive.function.client.WebClient {
	
	private final org.springframework.web.reactive.function.client.WebClient client;
	
	public static WebClient decorate(org.springframework.web.reactive.function.client.WebClient client) {
		if (client == null) {
			return null;
		}
		if (client instanceof WebClient) {
			return (WebClient) client;
		}
		return new WebClient(client);
	}	
	
	private WebClient(org.springframework.web.reactive.function.client.WebClient client) {
		this.client = client;
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<?> get() {
		return RequestHeadersUriSpec.decorate(client.get());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<?> head() {
		return RequestHeadersUriSpec.decorate(client.head());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec post() {
		return WebClient.RequestBodyUriSpec.decorate(client.post());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec put() {
		return RequestBodyUriSpec.decorate(client.put());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec patch() {
		return RequestBodyUriSpec.decorate(client.patch());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<?> delete() {
		return RequestHeadersUriSpec.decorate(client.delete());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<?> options() {
		return RequestHeadersUriSpec.decorate(client.options());
	}

	@Override
	public org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec method(HttpMethod method) {
		return RequestBodyUriSpec.decorate(client.method(method));
	}

	@Override
	public Builder mutate() {
		return client.mutate();
	}
	
	private static final class ResponseSpec implements org.springframework.web.reactive.function.client.WebClient.ResponseSpec {
		
		private final org.springframework.web.reactive.function.client.WebClient.ResponseSpec rs;
		
		public static org.springframework.web.reactive.function.client.WebClient.ResponseSpec decorate(org.springframework.web.reactive.function.client.WebClient.ResponseSpec rs) {
			if (rs == null) {
				return null;
			}
			if (rs instanceof ResponseSpec) {
				return (ResponseSpec) rs;
			}
			return new ResponseSpec(rs);
		}
		
		private ResponseSpec(org.springframework.web.reactive.function.client.WebClient.ResponseSpec rs) {
			this.rs = rs;
		}	

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec onStatus(Predicate<HttpStatus> statusPredicate,	Function<ClientResponse, Mono<? extends Throwable>> exceptionFunction) {
			return decorate(rs.onStatus(statusPredicate, exceptionFunction));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec onRawStatus(IntPredicate statusCodePredicate, Function<ClientResponse, Mono<? extends Throwable>> exceptionFunction) {
			return decorate(rs.onRawStatus(statusCodePredicate, exceptionFunction));
		}

		@Override
		public <T> Mono<T> bodyToMono(Class<T> elementClass) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.bodyToMono(elementClass));
		}

		@Override
		public <T> Mono<T> bodyToMono(ParameterizedTypeReference<T> elementTypeRef) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.bodyToMono(elementTypeRef));
		}

		@Override
		public <T> Flux<T> bodyToFlux(Class<T> elementClass) {
			return rs.bodyToFlux(elementClass);
		}

		@Override
		public <T> Flux<T> bodyToFlux(ParameterizedTypeReference<T> elementTypeRef) {
			return rs.bodyToFlux(elementTypeRef);
		}

		@Override
		public <T> Mono<ResponseEntity<T>> toEntity(Class<T> bodyClass) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntity(bodyClass));
		}

		@Override
		public <T> Mono<ResponseEntity<T>> toEntity(ParameterizedTypeReference<T> bodyTypeReference) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntity(bodyTypeReference));
		}

		@Override
		public <T> Mono<ResponseEntity<List<T>>> toEntityList(Class<T> elementClass) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntityList(elementClass));
		}

		@Override
		public <T> Mono<ResponseEntity<List<T>>> toEntityList(ParameterizedTypeReference<T> elementTypeRef) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntityList(elementTypeRef));
		}

		@Override
		public Mono<ResponseEntity<Void>> toBodilessEntity() {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toBodilessEntity());
		}

		@Override
		public <T> Mono<ResponseEntity<Flux<T>>> toEntityFlux(Class<T> elementType) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntityFlux(elementType));
		}

		@Override
		public <T> Mono<ResponseEntity<Flux<T>>> toEntityFlux(ParameterizedTypeReference<T> elementTypeReference) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntityFlux(elementTypeReference));
		}

		@Override
		public <T> Mono<ResponseEntity<Flux<T>>> toEntityFlux(BodyExtractor<Flux<T>, ? super ClientHttpResponse> bodyExtractor) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rs.toEntityFlux(bodyExtractor));
		}

	}
	
	private static final class RequestBodySpec implements org.springframework.web.reactive.function.client.WebClient.RequestBodySpec {

		private final org.springframework.web.reactive.function.client.WebClient.RequestBodySpec rbs;
		
		public static org.springframework.web.reactive.function.client.WebClient.RequestBodySpec decorate(org.springframework.web.reactive.function.client.WebClient.RequestBodySpec rbs) {
			if (rbs == null) {
				return null;
			}
			if (rbs instanceof RequestBodySpec) {
				return (RequestBodySpec) rbs;
			}
			return new RequestBodySpec(rbs);
		}
		
		private RequestBodySpec(org.springframework.web.reactive.function.client.WebClient.RequestBodySpec rbs) {
			this.rbs = rbs;
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec retrieve() {
			return ResponseSpec.decorate(rbs.retrieve());
		}

		@Override
		@Deprecated
		public Mono<ClientResponse> exchange() {
			return dtcookie.reactor.core.publisher.Mono.decorate(rbs.exchange());
		}
		
		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec accept(MediaType... acceptableMediaTypes) {
			return decorate(rbs.accept(acceptableMediaTypes));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec acceptCharset(Charset... acceptableCharsets) {
			return decorate(rbs.acceptCharset(acceptableCharsets));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec cookie(String name, String value) {
			return decorate(rbs.cookie(name, value));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec cookies(Consumer<MultiValueMap<String, String>> cookiesConsumer) {
			return decorate(rbs.cookies(cookiesConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec ifModifiedSince(ZonedDateTime ifModifiedSince) {
			return decorate(rbs.ifModifiedSince(ifModifiedSince));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec ifNoneMatch(String... ifNoneMatches) {
			return decorate(rbs.ifNoneMatch(ifNoneMatches));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec header(String headerName, String... headerValues) {
			return decorate(rbs.header(headerName, headerValues));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec headers(Consumer<HttpHeaders> headersConsumer) {
			return decorate(rbs.headers(headersConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec attribute(String name, Object value) {
			return decorate(rbs.attribute(name, value));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec attributes(Consumer<Map<String, Object>> attributesConsumer) {
			return decorate(rbs.attributes(attributesConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec contentLength(long contentLength) {
			return decorate(rbs.contentLength(contentLength));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec contentType(MediaType contentType) {
			return decorate(rbs.contentType(contentType));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> bodyValue(Object body) {
			return RequestHeadersSpec.decorate(rbs.bodyValue(body));
		}

		@Override
		public <T, P extends Publisher<T>> org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(P publisher, Class<T> elementClass) {
			return RequestHeadersSpec.decorate(rbs.body(publisher, elementClass));
		}

		@Override
		public <T, P extends Publisher<T>> org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(P publisher, ParameterizedTypeReference<T> elementTypeRef) {
			return RequestHeadersSpec.decorate(rbs.body(publisher, elementTypeRef));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(Object producer, Class<?> elementClass) {
			return RequestHeadersSpec.decorate(rbs.body(producer, elementClass));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(Object producer, ParameterizedTypeReference<?> elementTypeRef) {
			return RequestHeadersSpec.decorate(rbs.body(producer, elementTypeRef));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(BodyInserter<?, ? super ClientHttpRequest> inserter) {
			return RequestHeadersSpec.decorate(rbs.body(inserter));
		}

		@Override
		@Deprecated
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> syncBody(Object body) {
			return RequestHeadersSpec.decorate(rbs.syncBody(body));
		}

		@Override
		@Deprecated
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec context(Function<Context, Context> contextModifier) {
			return decorate(rbs.context(contextModifier));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec httpRequest(Consumer<ClientHttpRequest> requestConsumer) {
			return decorate(rbs.httpRequest(requestConsumer));
		}

		@Override
		public <V> Mono<V> exchangeToMono(Function<ClientResponse, ? extends Mono<V>> responseHandler) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rbs.exchangeToMono(responseHandler));
		}

		@Override
		public <V> Flux<V> exchangeToFlux(Function<ClientResponse, ? extends Flux<V>> responseHandler) {
			return rbs.exchangeToFlux(responseHandler);
		}

	}
	
	
	private static final class RequestBodyUriSpec implements org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec {

		private final org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec rbus;
		
		public static org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec decorate(org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec rbus) {
			if (rbus == null) {
				return null;
			}
			if (rbus instanceof RequestBodyUriSpec) {
				return (RequestBodyUriSpec) rbus;
			}
			return new RequestBodyUriSpec(rbus);
		}
		
		private RequestBodyUriSpec(org.springframework.web.reactive.function.client.WebClient.RequestBodyUriSpec rbus) {
			this.rbus = rbus;
		}

		@Override
		@Deprecated
		public Mono<ClientResponse> exchange() {
			return dtcookie.reactor.core.publisher.Mono.decorate(rbus.exchange());
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec retrieve() {
			return ResponseSpec.decorate(rbus.retrieve());
		}
		
		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec accept(MediaType... acceptableMediaTypes) {
			return RequestBodySpec.decorate(rbus.accept(acceptableMediaTypes));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec acceptCharset(Charset... acceptableCharsets) {
			return RequestBodySpec.decorate(rbus.acceptCharset(acceptableCharsets));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec cookie(String name, String value) {
			return RequestBodySpec.decorate(rbus.cookie(name, value));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec cookies(Consumer<MultiValueMap<String, String>> cookiesConsumer) {
			return RequestBodySpec.decorate(rbus.cookies(cookiesConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec ifModifiedSince(ZonedDateTime ifModifiedSince) {
			return RequestBodySpec.decorate(rbus.ifModifiedSince(ifModifiedSince));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec ifNoneMatch(String... ifNoneMatches) {
			return RequestBodySpec.decorate(rbus.ifNoneMatch(ifNoneMatches));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec header(String headerName, String... headerValues) {
			return RequestBodySpec.decorate(rbus.header(headerName, headerValues));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec headers(Consumer<HttpHeaders> headersConsumer) {
			return RequestBodySpec.decorate(rbus.headers(headersConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec attribute(String name, Object value) {
			return RequestBodySpec.decorate(rbus.attribute(name, value));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec attributes(Consumer<Map<String, Object>> attributesConsumer) {
			return RequestBodySpec.decorate(rbus.attributes(attributesConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec contentLength(long contentLength) {
			return RequestBodySpec.decorate(rbus.contentLength(contentLength));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec contentType(MediaType contentType) {
			return RequestBodySpec.decorate(rbus.contentType(contentType));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> bodyValue(Object body) {
			return RequestHeadersSpec.decorate(rbus.bodyValue(body));
		}

		@Override
		public <T, P extends Publisher<T>> org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(P publisher, Class<T> elementClass) {
			return RequestHeadersSpec.decorate(rbus.body(publisher, elementClass));
		}

		@Override
		public <T, P extends Publisher<T>> org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(P publisher, ParameterizedTypeReference<T> elementTypeRef) {
			return RequestHeadersSpec.decorate(rbus.body(publisher, elementTypeRef));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(Object producer, Class<?> elementClass) {
			return RequestHeadersSpec.decorate(rbus.body(producer, elementClass));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(Object producer, ParameterizedTypeReference<?> elementTypeRef) {
			return RequestHeadersSpec.decorate(rbus.body(producer, elementTypeRef));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> body(BodyInserter<?, ? super ClientHttpRequest> inserter) {
			return RequestHeadersSpec.decorate(rbus.body(inserter));
		}

		@Override
		@Deprecated
		public org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?> syncBody(Object body) {
			return RequestHeadersSpec.decorate(rbus.syncBody(body));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec uri(URI uri) {
			return RequestBodySpec.decorate(rbus.uri(uri));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec uri(String uri, Object... uriVariables) {
			return RequestBodySpec.decorate(rbus.uri(uri, uriVariables));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec uri(String uri, Map<String, ?> uriVariables) {
			return RequestBodySpec.decorate(rbus.uri(uri, uriVariables));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec uri(String uri, Function<UriBuilder, URI> uriFunction) {
			return RequestBodySpec.decorate(rbus.uri(uri, uriFunction));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec uri(Function<UriBuilder, URI> uriFunction) {
			return RequestBodySpec.decorate(rbus.uri(uriFunction));
		}

		@Override
		@Deprecated
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec context(Function<Context, Context> contextModifier) {
			return RequestBodySpec.decorate(rbus.context(contextModifier));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.RequestBodySpec httpRequest(Consumer<ClientHttpRequest> requestConsumer) {
			return RequestBodySpec.decorate(rbus.httpRequest(requestConsumer));
		}

		@Override
		public <V> Mono<V> exchangeToMono(Function<ClientResponse, ? extends Mono<V>> responseHandler) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rbus.exchangeToMono(responseHandler));
		}

		@Override
		public <V> Flux<V> exchangeToFlux(Function<ClientResponse, ? extends Flux<V>> responseHandler) {
			return rbus.exchangeToFlux(responseHandler);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static final class RequestHeadersSpec<S extends org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<S>> implements org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<S> {
		
		private final org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<S> rhs;
		
		public static <X extends org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<X>> org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<X> decorate(org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<X> rhs) {
			if (rhs == null) {
				return null;
			}
			if (rhs instanceof RequestHeadersSpec) {
				return (RequestHeadersSpec<X>) rhs;
			}
			return new RequestHeadersSpec<X>(rhs);
		}
		
		private RequestHeadersSpec(org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<S> rhs) {
			this.rhs = rhs;
		}

		@Override
		@Deprecated
		public Mono<ClientResponse> exchange() {
			return dtcookie.reactor.core.publisher.Mono.decorate(rhs.exchange());
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec retrieve() {
			return ResponseSpec.decorate(rhs.retrieve());
		}

		@Override
		public S accept(MediaType... acceptableMediaTypes) {
			return (S) RequestHeadersSpec.decorate(rhs.accept(acceptableMediaTypes));
		}

		@Override
		public S acceptCharset(Charset... acceptableCharsets) {
			return (S) RequestHeadersSpec.decorate(rhs.acceptCharset(acceptableCharsets));
		}

		@Override
		public S cookie(String name, String value) {
			return (S) RequestHeadersSpec.decorate(rhs.cookie(name, value));
		}

		@Override
		public S cookies(Consumer<MultiValueMap<String, String>> cookiesConsumer) {
			return (S) RequestHeadersSpec.decorate(rhs.cookies(cookiesConsumer));
		}

		@Override
		public S ifModifiedSince(ZonedDateTime ifModifiedSince) {
			return (S) RequestHeadersSpec.decorate(rhs.ifModifiedSince(ifModifiedSince));
		}

		@Override
		public S ifNoneMatch(String... ifNoneMatches) {
			return (S) RequestHeadersSpec.decorate(rhs.ifNoneMatch(ifNoneMatches));
		}

		@Override
		public S header(String headerName, String... headerValues) {
			return (S) RequestHeadersSpec.decorate(rhs.header(headerName, headerValues));
		}

		@Override
		public S headers(Consumer<HttpHeaders> headersConsumer) {
			return (S) RequestHeadersSpec.decorate(rhs.headers(headersConsumer));
		}

		@Override
		public S attribute(String name, Object value) {
			return (S) RequestHeadersSpec.decorate(rhs.attribute(name, value));
		}

		@Override
		public S attributes(Consumer<Map<String, Object>> attributesConsumer) {
			return (S) RequestHeadersSpec.decorate(rhs.attributes(attributesConsumer));
		}

		@Override
		@Deprecated
		public S context(Function<Context, Context> contextModifier) {
			return (S) RequestHeadersSpec.decorate(rhs.context(contextModifier));
		}

		@Override
		public S httpRequest(Consumer<ClientHttpRequest> requestConsumer) {
			return (S) RequestHeadersSpec.decorate(rhs.httpRequest(requestConsumer));
		}

		@Override
		public <V> Mono<V> exchangeToMono(Function<ClientResponse, ? extends Mono<V>> responseHandler) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rhs.exchangeToMono(responseHandler));
		}

		@Override
		public <V> Flux<V> exchangeToFlux(Function<ClientResponse, ? extends Flux<V>> responseHandler) {
			return rhs.exchangeToFlux(responseHandler);
		}

	}
	
	@SuppressWarnings("unchecked")
	private static final class RequestHeadersUriSpec<P extends org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<P>> 
	implements
	org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<P> {
		
		private final org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<P> rhs;
		
		public static
		<K extends org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<K>>
		org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<K> 
		decorate(
				org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<?> rhs
		) {
			if (rhs == null) {
				return null;
			}
			if (rhs instanceof RequestHeadersUriSpec) {
				return (RequestHeadersUriSpec<K>) rhs;
			}
			return new RequestHeadersUriSpec<K>((org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<K>) rhs);
		}
		
		private RequestHeadersUriSpec(org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec<P> rhs) {
			this.rhs = rhs;
		}
		
		@Override
		@Deprecated
		public Mono<ClientResponse> exchange() {
			return dtcookie.reactor.core.publisher.Mono.decorate(rhs.exchange());
		}

		@Override
		public P uri(String uri, Object... uriVariables) {
			return (P) RequestHeadersUriSpec.decorate(rhs.uri(uri, uriVariables));
		}

		@Override
		public P uri(URI uri) {
			return (P) RequestHeadersUriSpec.decorate(rhs.uri(uri));
		}

		@Override
		public P uri(String uri, Map<String, ?> uriVariables) {
			return (P) RequestHeadersUriSpec.decorate(rhs.uri(uri, uriVariables));
		}

		@Override
		public P uri(String uri, Function<UriBuilder, URI> uriFunction) {
			return (P) RequestHeadersUriSpec.decorate(rhs.uri(uri, uriFunction));
		}

		@Override
		public P uri(Function<UriBuilder, URI> uriFunction) {
			return (P) RequestHeadersUriSpec.decorate(rhs.uri(uriFunction));
		}

		@Override
		public P accept(MediaType... acceptableMediaTypes) {
			return (P) RequestHeadersUriSpec.decorate(rhs.accept(acceptableMediaTypes));
		}

		@Override
		public P acceptCharset(Charset... acceptableCharsets) {
			return (P) RequestHeadersUriSpec.decorate(rhs.acceptCharset(acceptableCharsets));
		}

		@Override
		public P cookie(String name, String value) {
			return (P) RequestHeadersUriSpec.decorate(rhs.cookie(name, value));
		}

		@Override
		public P cookies(Consumer<MultiValueMap<String, String>> cookiesConsumer) {
			return (P) RequestHeadersUriSpec.decorate(rhs.cookies(cookiesConsumer));
		}

		@Override
		public P ifModifiedSince(ZonedDateTime ifModifiedSince) {
			return (P) RequestHeadersUriSpec.decorate(rhs.ifModifiedSince(ifModifiedSince));
		}

		@Override
		public P ifNoneMatch(String... ifNoneMatches) {
			return (P) RequestHeadersUriSpec.decorate(rhs.ifNoneMatch(ifNoneMatches));
		}

		@Override
		public P header(String headerName, String... headerValues) {
			return (P) RequestHeadersUriSpec.decorate(rhs.header(headerName, headerValues));
		}

		@Override
		public P headers(Consumer<HttpHeaders> headersConsumer) {
			return (P) RequestHeadersUriSpec.decorate(rhs.headers(headersConsumer));
		}

		@Override
		public P attribute(String name, Object value) {
			return (P) RequestHeadersUriSpec.decorate(rhs.attribute(name, value));
		}

		@Override
		public P attributes(Consumer<Map<String, Object>> attributesConsumer) {
			return (P) RequestHeadersUriSpec.decorate(rhs.attributes(attributesConsumer));
		}

		@Override
		public org.springframework.web.reactive.function.client.WebClient.ResponseSpec retrieve() {
			return ResponseSpec.decorate(rhs.retrieve());
		}

		@Override
		@Deprecated
		public P context(Function<Context, Context> contextModifier) {
			return (P) RequestHeadersUriSpec.decorate(rhs.context(contextModifier));
		}

		@Override
		public P httpRequest(Consumer<ClientHttpRequest> requestConsumer) {
			return (P) RequestHeadersUriSpec.decorate(rhs.httpRequest(requestConsumer));
		}

		@Override
		public <V> Mono<V> exchangeToMono(Function<ClientResponse, ? extends Mono<V>> responseHandler) {
			return dtcookie.reactor.core.publisher.Mono.decorate(rhs.exchangeToMono(responseHandler));
		}

		@Override
		public <V> Flux<V> exchangeToFlux(Function<ClientResponse, ? extends Flux<V>> responseHandler) {
			return rhs.exchangeToFlux(responseHandler);
		}
	}
	

}
