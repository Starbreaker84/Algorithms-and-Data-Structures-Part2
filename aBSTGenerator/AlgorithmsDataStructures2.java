import java.util.*;

public class AlgorithmsDataStructures2
{
    private static int[] generateArray(int[] aBST, int[] a, int index) {
        if (a.length == 0){
            return aBST;
        }
        aBST[index] = a[a.length / 2];
        generateArray(aBST, Arrays.copyOfRange(a, 0, a.length / 2), 2 * index + 1);
        generateArray(aBST, Arrays.copyOfRange(a, a.length / 2 + 1, a.length), 2 * index + 2);
        return aBST;
    }

    public static int[] GenerateBBSTArray(int[] a)
    {
        Arrays.sort(a);
        int[] aBST = new int[a.length];

        return generateArray(aBST, a, 0) ;
    }
}
