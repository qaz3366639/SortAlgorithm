package com.flamingo.demoapp;

import java.util.Arrays;


/**
 * Description:  八大常用排序算法，方法中并不一定是最优算法，如果想要达到最优的时间复杂度，需要对其进行修改
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
        mainClass.shellSort(a);
        System.out.println(Arrays.toString(a));
    }

    /**
     * 插入排序
     * 选择待排序数组中第二个数，然后将其与前面的数相比较，如果发现比这个数大（小）的数，则将该数后移一位
     * 直到前面没有比这个数大（小）的数，则把该数放置在当前位置 该算法时间复杂度为O（n^2）
     * @param a
     */
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

    /**
     * 选择排序
     * 第一次循环从待排序数组中选择出最小的数，将其放置在第一位，第二次则在第一位之后的数中选出最小的
     * 放置在第二位，以此类推 时间复杂度为O(n^2)
     * @param a
     */
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

    /**
     * 冒泡排序
     * 选择第一个数，将其与后一位数进行比较，如果比后一位数小则交换两者位置，直至队尾
     * 重复上一步，以此类推
     * @param a
     */
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

    /**
     * 堆排序
     * 首先将待排序数组构造成大（小）顶堆，堆满足完全二叉树的规则。堆中的第i个节点（从0开始计），其左子树为
     * 2i，右子树为2i+1，将构造好的大（小）顶堆的根节点与其最末尾的节点交换位置，然后再对剩下的数构造大顶堆，
     * 以此类推
     * @param a
     */
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

    /**
     * 快速排序
     * 1、选择一个数（通常是第一个数a0），从队尾开始（j=a.length），寻找比a0小的数，并将其与a0的位置交换
     * 2、从队首开始(i=0)，寻找比a0大的数，并将其与a0的位置交换。
     * 3、重复1跟2，直至i==j，此时左边均比a0小，右边均比a0大，对左右两个部分继续进行上述步骤。直至剩下部分为1，排序完成
     * 使用递归方式实现
     * @param a
     */
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

    /**
     * 归并排序
     * 将待排序数组进行分组，最初两个元素为一组，不足2个的单独一个数一组，然后两两合并
     * 合并规则为：归并过程为：比较a[i]和a[j]的大小，若a[i]≤a[j]，则将第一个有序表中的元素a[i]复制到r[k]中，
     * 并令i和k分别加上1；否则将第二个有序表中的元素a[j]复制到r[k]中，并令j和k分别加上1，如此循环下去，
     * 直到其中一个有序表取完，然后再将另一个有序表中剩余的元素复制到r中从下标k到下标t的单元。归并排序的
     * 算法我们通常用递归实现，先把待排序区间[s,t]以中点二分，接着把左边子区间排序，再把右边子区间排序，
     * 最后把左区间和右区间用一次归并操作合并成有序的区间[s,t]。
     * @param a
     * @param start
     * @param end
     */
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
     * LSD算法过程为：我们知道，任何一个阿拉伯数，它的各个位数上的基数都是以0~9来表示的。
     * 所以我们不妨把0~9视为10个桶。 我们先根据序列的个位数的数字来进行分类，将其分到指定的桶中。
     * 例如：R[0] = 50，个位数上是0，将这个数存入编号为0的桶中。分类后，我们在从各个桶中，
     * 将这些数按照从编号0到编号9的顺序依次将所有数取出来。这时，得到的序列就是个位数上呈递增趋势的序列。
     * 按照个位数排序： {50, 30, 0, 100, 11, 2, 123, 543, 187, 49}。
     *接下来，可以对十位数、百位数也按照这种方法进行排序，最后就能得到排序完成的序列。
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
