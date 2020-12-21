package com.example.lib.sort_fun;

import javax.tools.Diagnostic;

public class SortFun {
    public static void main(String[] a) {
        int[] array = {10, 8, 9, 1, 2, 5, 3, 4, 6, 7};
        testFun(4,array);
        printArray(array);
    }

    private static void testFun(int flag,int[] array){
        switch (flag){
            case 1:
                testMaoPao(array);
                break;
            case 2:
                testKuaiSu(array);
                break;
            case 3:
                testSelectionSort(array);
                break;
            case 4:
                testInsertionSort(array);
            default:
                break;
        }
    }

    /**
     * 插入排序
     * 依次向前扫描已经排序的数列，将其插入到合适的位置
     * @param array
     */
    private static void testInsertionSort(int[] array) {
        int length = array.length;
        int current;
        for (int i = 0; i < length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {
                array[preIndex + 1] = array[preIndex];
                preIndex--;
            }
            array[preIndex + 1] = current;
        }
    }

    /**
     * 选择排序
     * 选择最小的数放在最前面
     * @param array
     */
    private static void testSelectionSort(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            int min=array[i];
            int index=i;
            for (int j = i; j < length; j++) {
                if (array[j]<min){
                    min=array[j];
                    index=j;
                }
            }
            array[index]=array[i];
            array[i]=min;
        }
    }

    private static void testKuaiSu(int[] array) {

    }

    /**
     * 快速排序方法
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] QuickSort(int[] array, int start, int end) {
        if (array.length < 1 || start < 0 || end >= array.length || start > end) return null;
        int smallIndex = partition(array, start, end);
        if (smallIndex > start)
            QuickSort(array, start, smallIndex - 1);
        if (smallIndex < end)
            QuickSort(array, smallIndex + 1, end);
        return array;
    }
    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++)
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex)
                    swap(array, i, smallIndex);
            }
        return smallIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * 冒泡排序（小到大排序）
     * 前后两个相邻的元素相互比较，小数放前面，大数放后面
     * @param array
     */
    private static void testMaoPao(int[] array) {
        int l = array.length;
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l-1-i; j++) {
                if (array[j] > array[j+1]) {
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }

    }

    private static void printArray(int[] array) {
        StringBuilder builder = new StringBuilder("{");
        for (int i = 0; i < array.length - 1; i++) {
            builder.append(array[i]).append(",");
        }
        builder.append(array[array.length - 1]).append("}");
        System.out.println(builder.toString());
    }
}
