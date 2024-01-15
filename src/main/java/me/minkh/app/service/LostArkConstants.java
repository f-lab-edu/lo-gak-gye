package me.minkh.app.service;

import java.util.Map;

public final class LostArkConstants {

    private LostArkConstants() {
    }

    public static final String NIGHTMARE = "악몽";
    public static final String SALVATION = "구원";
    public static final String DOMINION = "지배";
    public static final String ENTROPY = "사멸";
    public static final String HALLUCINATION = "환각";

    public static final String VANGUARD = "선봉대";
    public static final String EXPERT = "달인";

    public static final String CURSED_DOLL = "저주받은 인형";
    public static final String ADRENALINE = "아드레날린";
    public static final String SHARP_BLUNT = "예리한 둔기";
    public static final String BLITZ_COMMANDER = "돌격대장";

    public static final String CRITICAL = "치명";
    public static final String SWIFTNESS = "신속";

    public static final String HELM = "투구";
    public static final String CHESTPIECE = "상의";
    public static final String PANTS = "하의";
    public static final String GLOVES = "장갑";
    public static final String PAULDRONS = "어깨";

    public static final Map<Integer, Double> ELIXIR_TO_ATTACK_INCREASE_MAP;
    public static final Map<Integer, Double> CURSED_DOLL_TO_ATTACK_INCREASE_MAP;
    public static final Map<Integer, Double> ADRENALINE_TO_ATTACK_INCREASE_MAP;
    public static final Map<Integer, Double> ADRENALINE_TO_CRITICAL_HIT_RATE_MAP;

    static {
        ELIXIR_TO_ATTACK_INCREASE_MAP = Map.of(
                0, 0.0,
                1, 0.23,
                2, 0.47,
                3, 0.72,
                4, 1.08,
                5, 1.44
        );

        CURSED_DOLL_TO_ATTACK_INCREASE_MAP = Map.of(
                0, 0.0,
                1, 3.0,
                2, 8.0,
                3, 16.0
        );

        ADRENALINE_TO_ATTACK_INCREASE_MAP = Map.of(
                0, 0.0,
                1, 1.8,
                2, 3.6,
                3, 6.0
        );

        ADRENALINE_TO_CRITICAL_HIT_RATE_MAP = Map.of(
                0, 0.0,
                1, 5.0,
                2, 10.0,
                3, 15.0
        );
    }
}
