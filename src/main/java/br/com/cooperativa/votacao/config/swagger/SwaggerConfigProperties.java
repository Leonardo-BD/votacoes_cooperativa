package br.com.cooperativa.votacao.config.swagger;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("info.app")
public class SwaggerConfigProperties {

	private String name;
	private String version;
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
