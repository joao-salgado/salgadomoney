package com.salgado.api.config.property;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("salgadomoney")
public class SalgadomoneyApiProperty {
	
	private String allowedOrigin = "http://localhost:8000";
	
	public void setAllowedOrigin(String allowedOrigin) {
		this.allowedOrigin = allowedOrigin;
	}

	public String getAllowedOrigin() {
		return allowedOrigin;
	}

	private final Security security = new Security();

	public Security getSecurity() {
		return security;
	}

	public static class Security {
		
		private boolean enableHttps;

		public boolean isEnableHttps() {
			return enableHttps;
		}

		public void setEnableHttps(boolean enableHttps) {
			this.enableHttps = enableHttps;
		}
	}
	
	
	
}
