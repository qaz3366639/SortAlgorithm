package com.flamingo.demoapp;

import java.util.Arrays;

import static android.R.attr.max;

/**
 * Description:  八大常用排序算法，方法中并不一定是最优算法
 * Author:       WuRuiqiang (263454190@qq.com)
 * CreateDate:   2016/9/20-14:23
 * UpdateUser:
 * UpdateDate:
 * UpdateRemark:
 * Version:      [v1.0]
 */
public class MainClass {

    public static void main(String[] args) {
        int[] a = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51};
        MainClass mainClass = new MainClass();
        long before = System.currentTimeMillis();
        mainClass.shellSort(a);
        long time = System.currentTimeMillis() - before;
        System.out.println(time + "time");
        System.out.println(Arrays.toString(a));
    }

    private void insertSort(int[] a) {

        int temp;
        for (int i = 1; i < a.length; i++) {

            int j = i - 1;
            temp = a[i];
            for (; j >= 0 && temp < a[j]; j--) {
                a[j + 1] = a[j];
            }
            a[j + 1] = temp;
        }
    }

    /**
     * 希尔排序
     * 1.选定一个整数d（该数通常是一个大于待排序数据长度的一半的最小整数）
     * 2.将待排序数组以该整数为间隔分为若干组，分别对每组进行直接插入排序
     * 3.调整d为原来的一半（同样是一个大于d的一半的最小整数）重复第二步，直至d最后等于1，排序完成
     *
     * @param a
     */
    private void shellSort(int[] a) {
        double length = a.length;
        int temp = 0;

        while (true) {
            length = Math.ceil(length / 2);
            int d = (int) length;

            for (int i = 0; i < d; i++) {
                int j = i;

                for (; j < a.length; j += d) {
                    int k = j - d;
                    temp = a[j];
                    for (; k >= 0 && temp > a[k]; k -= d) {
                        a[k + d] = a[k];
                    }
                    a[k + d] = temp;
                }

            }
            if (d == 1) {
                break;
            }
        }
    }

    private void chooseSort(int[] a) {

        for (int i = 0; i < a.length; i++) {
            int temp;
            int currentMin = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[currentMin] > a[j]) {
                    currentMin = j;
                }
            }
            temp = a[currentMin];
            a[currentMin] = a[i];
            a[i] = temp;
        }
    }

    public void heapSort2(int[] a) {
        System.out.println("开始排序");
        int arrayLength = a.length;
        //循环建堆
        for (int i = 0; i < arrayLength - 1; i++) {
            //建堆
            buildMaxHeap2(a, arrayLength - 1 - i);
            //交换堆顶和最后一个元素
            swap2(a, 0, arrayLength - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }


    private void swap2(int[] data, int i, int j) {
        // TODO Auto-generated method stub
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    //对data数组从0到lastIndex建大顶堆
    private void buildMaxHeap2(int[] data, int lastIndex) {
        // TODO Auto-generated method stub
        //从lastIndex处节点（最后一个节点）的父节点开始

        for (int i = (lastIndex - 1) / 2; i >= 0; i--) {
            //k保存正在判断的节点
            int k = i;
            //如果当前k节点的子节点存在
            while (k * 2 + 1 <= lastIndex) {
                //k节点的左子节点的索引
                int biggerIndex = 2 * k + 1;
                //如果biggerIndex小于lastIndex，即biggerIndex+1代表的k节点的右子节点存在
                if (biggerIndex < lastIndex) {
                    //若果右子节点的值较大
                    if (data[biggerIndex] < data[biggerIndex + 1]) {

                        //biggerIndex总是记录较大子节点的索引
                        biggerIndex++;
                    }
                }

                //如果k节点的值小于其较大的子节点的值
                if (data[k] < data[biggerIndex]) {
                    //交换他们
                    swap(data, k, biggerIndex);
                    //将biggerIndex赋予k，开始while循环的下一次循环，重新保证k节点的值大于其左右子节点的值
                    k = biggerIndex;
                } else {
                    break;
                }
            }
        }
    }

    private void bubbleSort(int[] a) {
        for (int i = 1; i <= a.length; i++) {
            for (int j = 0; j < a.length - i; j++) {
                if (a[j] < a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
    }

    private void heapSort(int[] a) {

        for (int i = 0; i < a.length - 1; i++) {
            buildMaxHeap(a, a.length - 1 - i);
            swap(a, 0, a.length - 1 - i);
            System.out.println(Arrays.toString(a));
        }
    }

    private void buildMaxHeap(int[] a, int lastIndex) {

        for (int i = lastIndex / 2; i >= 0; i--) {

            int k = i;
            while (k * 2 + 1 <= lastIndex) {
                int currentChild = 2 * k + 1;
                if (currentChild < lastIndex && a[currentChild] < a[currentChild + 1]) {
                    currentChild ++;
                }

                if (a[k] < a[currentChild]) {
                    swap(a, k, currentChild);
                    k = currentChild;
                } else {
                    break;
                }
            }
        }
    }

    private void quickSort(int[] a) {
        int start = 0, end = a.length - 1;

        getMiddle(a, start, end);
    }

    private void getMiddle(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int middle = group(a, start, end);
        getMiddle(a, start, middle - 1);
        getMiddle(a, middle + 1, end);
    }

    private int group(int[] a, int start, int end) {
        int k = a[start];

        while (start < end) {

            while (end > start && a[end] >= k) {
                end--;
            }
            a[start] = a[end];

            while (start < end && a[start] <= k) {
                start++;
            }
            a[end] = a[start];
        }
        a[start] = k;
        return start;
    }

    private void mergeSort(int[] a, int start, int end) {
        if (start < end) {
            int center = (end + start) / 2;
            mergeSort(a, start, center);
            mergeSort(a, center + 1, end);
            merge(a, start, center, end);
        }
    }

    private void merge(int[] a, int left, int center, int right) {
        int[] newA = new int[right - left + 1];
        int index = 0;
        int r = center + 1;
        int l = left;

        while (l <= center && r <= right) {
            if (a[l] <= a[r]) {
                newA[index] = a[l];
                index++;
                l++;
            } else {
                newA[index] = a[r];
                index++;
                r++;
            }
        }

        while (l <= center) {
            newA[index++] = a[l++];
        }

        while (r <= right) {
            newA[index++] = a[r++];
        }

        index = 0;
        while (left <= right) {
            a[left] = newA[index];
            index++;
            left++;
        }
    }

    /**
     * 基数排序（LSD）
     * @param a
     */
    private void radixSort(int[] a) {
        int bits = 0;//数组中最大值的位数
        int d = 1;
        int[] bitCount = new int[10];//统计待排序数组中对应位数的个数如 bitCount[1]为2表示该位数字为1的数字有2个
        int[][] temp = new int[10][a.length];
        // 获取数组中最大值
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }

        while (max > 0) {
            max /= 10;
            bits++;
        }

        for (int i = 0; i < bits; i++) {
            for (int j = 0; j < a.length; j++) {
                int bit = (a[j] / d) % 10;
                temp[bit][bitCount[bit]] = a[j];
                bitCount[bit]++;
            }

            for (int j = 0, index = 0; j < temp.length; j++) {
                if (bitCount[j] != 0) {
                    for (int k = 0; k < bitCount[j]; k++) {
                        a[index] = temp[j][k];
                        index++;
                    }
                    bitCount[j] = 0;
                }
            }

            d *= 10;
        }
    }

    private void swap(int[] a, int from, int to) {
        int temp = a[from];
        a[from] = a[to];
        a[to] = temp;
    }
}
