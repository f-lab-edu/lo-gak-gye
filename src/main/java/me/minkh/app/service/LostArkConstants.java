package me.minkh.app.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class LostArkConstants {

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

    public static final Map<Integer, Double> elixirToAttackIncreaseMap = new ConcurrentHashMap<>();
    public static final Map<Integer, Double> cursedDollToAttackIncreaseMap = new ConcurrentHashMap<>();
    public static final Map<Integer, Double> adrenalineToAttackIncreaseMap = new ConcurrentHashMap<>();
    public static final Map<Integer, Double> adrenalineToCriticalHitRateMap = new ConcurrentHashMap<>();

    static {
        elixirToAttackIncreaseMap.put(0, 0.0);
        elixirToAttackIncreaseMap.put(1, 0.23);
        elixirToAttackIncreaseMap.put(2, 0.47);
        elixirToAttackIncreaseMap.put(3, 0.72);
        elixirToAttackIncreaseMap.put(4, 1.08);
        elixirToAttackIncreaseMap.put(5, 1.44);

        cursedDollToAttackIncreaseMap.put(0, 0.0);
        cursedDollToAttackIncreaseMap.put(1, 3.0);
        cursedDollToAttackIncreaseMap.put(2, 8.0);
        cursedDollToAttackIncreaseMap.put(3, 16.0);

        adrenalineToAttackIncreaseMap.put(0, 0.0);
        adrenalineToAttackIncreaseMap.put(1, 1.8);
        adrenalineToAttackIncreaseMap.put(2, 3.6);
        adrenalineToAttackIncreaseMap.put(3, 6.0);

        adrenalineToCriticalHitRateMap.put(0, 0.0);
        adrenalineToCriticalHitRateMap.put(1, 5.0);
        adrenalineToCriticalHitRateMap.put(2, 10.0);
        adrenalineToCriticalHitRateMap.put(3, 15.0);
    }
}
