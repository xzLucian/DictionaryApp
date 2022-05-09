package top.lucian.dictionary.util;

public class StringUtil {
    /**
     * 将字符串转换成整型数组(以逗号为分割线)
     * "2，3，4，5" ==》 [2,3,4,5]
     * @param value
     * @return
     */
    public static int[] stringConvertInt(String value) {
        int[] intArr;
        if(isNull(value)){
            intArr = new int[0];
        }else{
            String[] valueArr = value.split(",");
            intArr = new int[valueArr.length];
            for (int i = 0; i < valueArr.length; i++) {
                intArr[i] = Integer.parseInt(valueArr[i]);
            }
        }
        return intArr;
    }

    private static boolean isNull(String param){
        if(param==null||param.isEmpty()||param.trim().equals("")) return true;
        return false;
    }



    public static boolean inArray(int[] array, int value) {
        String s = value + "";  //先转换为String类型
        for (int i : array) {
            if (s.equals(i + ""))
                return true;
        }
        return false;
    }
}
