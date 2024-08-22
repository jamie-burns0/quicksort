package me.jamieburns.structures.quicksort;

import java.util.List;
import java.util.LinkedList;
import java.util.Deque;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Arrays;

public class Quicksort
{
    private int[] list;

    public Quicksort( List<Integer> list )
    {
        this( list.stream().mapToInt( Integer::intValue ).toArray() );
    }

    public Quicksort( int[] list )
    {
        this.list = list;
    }

    public List<Integer> sort()
    {
        quicksort();
        return Arrays.stream( list ).boxed().collect( Collectors.toList() );
    }

    private void quicksort()
    {
        Deque<Integer> stack = new LinkedList<>();

        stack.push( 0 );
        stack.push( list.length - 1 );

        while( stack.isEmpty() == false )
        {
            var right = stack.pop().intValue();
            var left = stack.pop().intValue();

            if( right <= left )
            {
                continue;
            }

            var pivotValue = list[ right - ( ( right - left ) >>> 1 ) ]; // middle value

            var pivotIndex = partition( left, right, pivotValue );

            if( right - left <= 2 )
            {
                continue;
            }

            if( pivotIndex > left )
            {
                stack.push( left );
                stack.push( pivotIndex );
            }

            if( pivotIndex + 1 < right )
            {
                stack.push( pivotIndex + 1 );
                stack.push( right );
            }
        }
    }

    private int partition( int left, int right, int pivotValue )
    {
        var lp = left - 1;
        var rp = right + 1;

        while( true )
        {
            do
            {
                lp++;
            } while( list[ lp ] < pivotValue );

            do
            {
                rp--;
            } while( list[ rp ] > pivotValue );

            if( lp >= rp)
            {
                return rp;
            }

            swap( lp, rp );

        }
    }

    private void swap( int left, int right )
    {
        var temp = list[ left ];
        list[ left ] = list[ right ];
        list[ right ] = temp;
    }


    public static void main(String... args)
    {
        var list = List.of( 10, 5, 15, 8, 7, 8, 2, 6, 4, 9, 1 );

        var quicksort = new Quicksort( new ArrayList<>( list ) );
        System.out.println( quicksort.sort() );

        var list2 = List.of( 10, 1, 8, 8, 8, 8, 8, 1, 10 );

        var quicksort2 = new Quicksort( new ArrayList<>( list2 ) );
        System.out.println( quicksort2.sort());
    }
}
