package test;

public class TestNum {
    public static void main(String[] args) {

        String v1 ="";
        String v2 ="12345";

     /*   int v11 = Integer.parseInt(v1);
        int v22 = Integer.parseInt(v2);

        if(v11>v22){
            System.out.println(1);
        }else{
            System.out.println(-1);
        }*/

        String s = null;
        String s1 = "";
        System.out.println(s);
        System.out.println(s1);
        if("".endsWith(s1)){
            System.out.println(1111);
        }

        int a = compareVersion(v1,v2);
        System.out.println(a);
    }


    /**
     * 版本号比较
     *
     * @param v1
     * @param v2
     * @return 0代表相等，1代表左边大，-1代表右边大
     * Utils.compareVersion("1.0.358_20180820090554","1.0.358_20180820090553")=1
     */
    public static int compareVersion(String v1, String v2) {

        if(v1==""||v1==null||v2==""||v2==null){
            return 0;
        }

        if (v1.equals(v2)) {
            return 0;
        }
        String[] version1Array = v1.split("[._]");
        String[] version2Array = v2.split("[._]");
        int index = 0;
        int minLen = Math.min(version1Array.length, version2Array.length);
        long diff = 0;

        while (index < minLen
                && (diff = Long.parseLong(version1Array[index])
                - Long.parseLong(version2Array[index])) == 0) {
            index++;
        }
        if (diff == 0) {
            for (int i = index; i < version1Array.length; i++) {
                if (Long.parseLong(version1Array[i]) > 0) {
                    return 1;
                }
            }

            for (int i = index; i < version2Array.length; i++) {
                if (Long.parseLong(version2Array[i]) > 0) {
                    return -1;
                }
            }
            return 0;
        } else {
            return diff > 0 ? 1 : -1;
        }
    }

}

