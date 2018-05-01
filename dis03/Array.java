public class Array {

    public static int[] insert(int[] arr, int item, int position) {
        int[] result = new int[arr.length + 1];
        System.arraycopy(arr, 0, result, 0, position);
        result[position] = item;
        System.arraycopy(arr, position, result, position + 1, arr.length - position);
        return result;
    }

    public static void reverse(int[] arr) {
        int frond = 0;
        int end = arr.length - 1;
        int temp = 0;
        while (frond < end) {
            temp = arr[frond];
            arr[frond] = arr[end];
            arr[end] = temp;
            frond += 1;
            end -= 1;
        }
    }

    public static int[] replicate(int[] arr) {
        int totalNumber = 0;
        for (int i = 0; i < arr.length; i++) {
            totalNumber += arr[i];
        }

        int[] result = new int[totalNumber];
        int i = 0;
        for (int k = 0; k < arr.length; k++) {
            int replicate = arr[k];
            for (int j = 0; j < replicate; j++) {
                result[i] = replicate;
                i += 1;
            }
        }

        return result;
    }
} 