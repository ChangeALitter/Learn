package com.changw.learn.basic.java.pattern;

import com.google.common.collect.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Iterator;

import static com.google.common.collect.ImmutableSetMultimap.of;

@Data
@AllArgsConstructor
public class MyEvent {
    private int type;
    private String msg;

    public static final ImmutableSet<MyEvent> GOOD_EVENT = ImmutableSet.<MyEvent>builder()
            .add(new MyEvent(1, "game"))
            .build();

    public static void main(String[] args) {
        var immutableSet = ImmutableMultiset.of("a","f","e","b","a","d","a");
        var iterator = immutableSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        var immutableMap = ImmutableSetMultimap.of("a",1,"d",2,"b",3,"a",1);
        System.out.println(immutableMap.entries());
        var immutableListMap = ImmutableListMultimap.of("a",1,"d",2,"e",3,"b",1);
         System.out.println(immutableListMap.entries());
         System.out.println(immutableMap.inverse());
        System.out.println(ImmutableSortedMap.copyOf(immutableListMap.asMap()));
        System.out.println(ImmutableSortedSet.copyOf(immutableSet));
        System.out.println(ImmutableSortedMultiset.copyOf(immutableSet).asList().get(3));
        var table = HashBasedTable.<Integer, String, Integer>create();
        table.put(1,"one", 1);
        table.put(2,"tow", 2);
        table.put(3, "three",3);
        System.out.println(table.rowMap());
        System.out.println(table.columnMap());
        var biMap = HashBiMap.<Integer, String>create();
        biMap.put(1,"one");;
        biMap.put(2,"two");
        //biMap.forcePut(1,"2");
        System.out.println(biMap);
        System.out.println(biMap.inverse());

        var rangeSet = TreeRangeSet.<Integer>create();
        rangeSet.add(Range.closed(5,10));
        rangeSet.add(Range.closedOpen(11, 15));
        System.out.println(rangeSet);
        System.out.println(rangeSet.asRanges());
    }
}
