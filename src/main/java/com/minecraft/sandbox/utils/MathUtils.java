package com.minecraft.sandbox.utils;

import java.util.Random;

public final class MathUtils {
  /**
   * Calculates a probability with given values. Returns either the given amount or 0 depending on whether the probability happens or not.
   * @param rng a Random generator
   * @param maxValue the maximum value that can be randomly generated
   * @param probability the probability this event has to happen
   * @param amount amount to return if probability happens
   * @return
   */
  public static int getRandomAmount(Random rng, int maxValue, int probability, int amount) {
    int random = rng.nextInt(maxValue);

    if (random >= probability) {
      return amount;
    } else {
      return 0;
    }
  }

  // -----------------------

  private MathUtils() { throw new IllegalStateException(MathUtils.class.getName() + " is an utility class and should not be instantiated."); }
}
