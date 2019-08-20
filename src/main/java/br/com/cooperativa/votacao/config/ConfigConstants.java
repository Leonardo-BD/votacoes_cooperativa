package br.com.cooperativa.votacao.config;

public class ConfigConstants {
    public static final String SECRET = "senhatoken";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URL = "/authentication/login";

    private ConfigConstants(){}
}