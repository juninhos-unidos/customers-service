package br.com.customers.infrastructure.adapters.inbound.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    RESOURCE_NOT_FOUND("/resource-not-found", "Resource not found");

	private String title;
	private String uri;

	private ProblemType(String path, String title) {
		this.uri = "https://domain.com.br" + path;
		this.title = title;
	}

}
