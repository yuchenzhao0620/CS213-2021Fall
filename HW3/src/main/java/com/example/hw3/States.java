package com.example.hw3;

/**
 * An enum class, including the city for the Tri-Student
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
enum States {
    NY, CT;

    /**
     * Determines whether a string in this enum class.
     *
     * @param s a string to compare with this value
     * @return if is in this enum class or not
     */
    public static boolean contains(String s){
        for(States c : States.values()) {
            if(c.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}