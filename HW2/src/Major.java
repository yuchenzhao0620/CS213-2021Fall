/**
 * An enum class, including the major for the student
 *
 * @author yuchenzhao yz1116, Jinrui Li jl2340
 */
enum Major {
    CS, IT, BA, EE, ME;

    /**
     * Determines whether a string in this enum class.
     *
     * @param s a string to compare with this value
     * @return if is in this enum class or not
     */
    public static boolean contains(String s){
        for(Major m : Major.values()) {
            if(m.name().equals(s)) {
                return true;
            }
        }
        return false;
    }
}
