package pe.robertem.example.exchangeapplication.security;

public class JwtConstants {

    public static final String SIGNER_SECRET = "exchange";
    public static final Integer EXPIRATION_TIME_IN_MILLISECONDS = 300 * 1000;
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_PARAM = "Authorization";

}
