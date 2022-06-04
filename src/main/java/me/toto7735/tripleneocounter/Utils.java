package me.toto7735.tripleneocounter;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Utils {
    
    private static Map<UUID, Integer> count = new HashMap<>();

    public static Map<UUID, Integer> getTripleNeoCounts() {
        return count;
    }

    public static void addTripleNeoCount(UUID uuid, int amount) {
        count.putIfAbsent(uuid, 0);
        count.put(uuid, getTripleNeoCount(uuid) + amount);
    }

    public static int getTripleNeoCount(UUID uuid) {
        count.putIfAbsent(uuid, 0);
        return count.get(uuid);
    }

    public static void setup(Map<UUID, Integer> map, Map<UUID, Integer> map2) {
        count = map;
        failCount = map2;
    }

    private static Map<UUID, Integer> failCount = new HashMap<>();

    public static Map<UUID, Integer> getFailCounts() {
        return failCount;
    }

    public static void addFailCount(UUID uuid, int amount) {
        failCount.putIfAbsent(uuid, 0);
        failCount.put(uuid, getFailCount(uuid) + amount);
    }

    public static int getFailCount(UUID uuid) {
        failCount.putIfAbsent(uuid, 0);
        return failCount.get(uuid);
    }

}