package com.hp.utils;

public class AlipayConfig {

    /**
     * 沙箱的APP_ID
     */
    public static String APP_ID = "2016101900721169";
    /**
     * 应用私匙
     */
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCEdOoo0w8081vUfjqza617ypPcGhXV+8yPSo5+RmLDl9iA5NWuADcjkM8Weiw2H2mShYiYs1YpmjoLjBo3K7wo/VDkqDFJQ1p5Rl/MPfzGv3HGVLQceKEbjyTILgDXfKNq587SvHum35Mt1OL6tX62hxUN3o8Ukej+GStiWKhrGpAtx+qzybxb40Zkm7FlbFuUm7X2VpLNlE8B3osQilPgoitppmKwto/EhMAqsImYDQs1SI7h8rMQgLPHYi5ryPU0oNDi0F9DZFXs4N2nDVISKqSosoBPTJOj2Z05nlvrh39jhywLJ154U5HrOknWmx3SyPserxkFQ7Jp+ECuxU1dAgMBAAECggEAdWx8RdLN8WAosZlyCqtTsaGmMQLDBt9hukobRqC5X7JWkfXL5KR2PUZcAA4w8Y4pdmaA8PZTVTBPcvsyJmTUOvZO51af0tYt98NGm/b8B2miwHNI9NCP6rGcpwX5Yw8whE6aUMIdUJN1k7Tdv+p0OfZI2PHLoC22AsVna5kzPTaZt6b0HMMRDggdkOD+1PUc6pwqpEOBAQlTevotZEqO9nsi1SIrmKu0uHqAJQK7Oefm2JO4mVWrdpphEu+B7NXqyLg95YVSWfq968G7FU8MeJu9GiUqR9ERKNXKHIGzOPqf6VIaZ+vTd8VP4qQ9KRlySaSUhv9Niit08oezgZbTwQKBgQC8DjuboBKE7Vv/wsC+g4+wWPAXCoKQdi3tf4bMC1mLFv7qLKs8sskuev9Ln6ibbUXwiCILiRcpiN6ZbdnCC1uZ7lXqXT17QHudFdHVbvTNXWKv5xxg3F7MHCjPe/voN+2vyKthvFe/3nB5Yuj5dzLxqir8QUFeuODRkGuzjotULQKBgQC0UDC8AFYLutuGjdbp9JNfAFtxclMW2agcg7AsjKBZmqBzeRHCF/ZspC8MxwyinCVqygJPdEWvaesCsgslE8fgUtUs5lkIUDU4Qinbsq6dVrgu0pNpPsmyUZTzrW3cIbJVFHeaBhkCOWvqn63cGTZbnkPlNqgIl2KqkspOFYWr8QKBgB4KVAFPN8YeOJUJokqMr5ZnDqtPlX3IMxv+RCUyJvKuELiNvCjtEnAnW8LT1VZGxWZgCLg5BQcwgwaVYrS5pb9YMEdI77pRma2FP8jNebOYmFwrGgBrE4WddfN3Bc2ZqZFsB9yL/qR6vxLU9wxquyuZmFZFglDETb2MSXgcbCtdAoGAdUtU6HcvgiHje/v4uegnlb0dvwanlk2wVhw0FkbEaIV1qqgvLRt5aeLD2IQDbCQ3Euil6Bmy2xEDl5VKOqxLcVlw4uxVLls+w4IGyKvLev5Wgd7plaUKf70iMAG5XWFuKHpP8xnUWuYPlWiaKHseKSsi75BGbpBL1rn2cCT/94ECgYAPku2FjKq3XEJW4GMUjTQ3g0NXWm+BXHtmZ97CzGAZ/P5y51WKKhbtWGp3hNt/DZcc0O7pfhsYhcmYMYgyTF82AUpDEoW99FggrnIsCnrzKfyHX5Pu/+fRO25pihicI88lRZ7u4ohQe3JxT+WfCSYsvgREoqpKV4HoQj3eSKKaeg==";

    /**
     * 支付宝公匙
     */
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlCLdMq0FOklkR6DUwJ3pMF0EBMq3puJzxFemQTc/o57G50mlS56dyOpC3KH9c2MPd6zqjfR6lGMUuPhH6bPuQSKqnlLMHmMJCW6A0z8/8enFSwWnZpxPpOAV739FAJ5R5ajhhsX9yzgdgjy6vKEsP+uIFmr+GuEWcI85jGaA7BgbnMRKZEturiP493qr8BwbYrg6Irsj/26apGVLiBQwwsdQm0iho36ib7hPq8dIUa8cDkWu95P/tgzEIVXjWtg0skUNnpC4E/SDqRussVdO/JYD0UxxOIgiDlaMYX1LxXlDqzaLZf/IjptFanuzhDHvmDrWZK/0u6uOaHL4riUaFwIDAQAB";

    /**
     * 请求网关
     */
    public static String URL = "https://openapi.alipaydev.com/gateway.do";

    /**
     * 支付按成功跳转页面
     */
    public static String ROUTER_PAGE = "http://localhost:9528/login";

    public static String SIGN_TYPE = "RSA2";

    public static String CHARSET = "GBK";
}
