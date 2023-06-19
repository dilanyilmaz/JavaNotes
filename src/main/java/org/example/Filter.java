package org.example;

import java.net.StandardSocketOptions;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filter {
  public static void main(String[] args){

    List<String> lines = Arrays.asList("spring","node","vue","next");

    List<String> result = lines.stream()
        .filter(line -> !"vue".equals(line))
        .collect(Collectors.toList());     //collect the output
    result.forEach(System.out::println);

    //---------------------------------------------------------------------------------

    List<Temp> temps = Arrays.asList(
            new Temp("vue",1),
            new Temp("node",2),
            new Temp("next",3)
    );

    Temp result1 = temps.stream()
        .filter(x -> "node".equals(x.getName()))
        .findAny()
        .orElse(null);

    System.out.println(result1);

    Temp result2 = temps.stream()
        .filter(x->"vue".equals(x.getName().toLowerCase()))
        .findAny()
        .orElse(null);

    System.out.println(result2);

    //we want to "node" only. if 'findAny' then return found else return null
    //---------------------------------------------------------------------------------

    Temp result3 = temps.stream()
        .filter((p)->"node".equals(p.getName()) && 2 == p.getCode())
        .findAny()
        .orElse(null);

    System.out.println("result 3 : "+ result3);

    //-----or we coult use this

    Temp result4 = temps.stream()
        .filter(p->{
          if("node".equals(p.getName()) && 2 == p.getCode()){
            return true;
          }
          return false;
        }).findAny()
        .orElse(null);

    System.out.println("result 4 : " + result4);

    //---------------------------------------------------------------------------------
    String name = temps.stream()
        .filter(x->"node".equals(x.getName()))
        .map(Temp::getName)
        .findAny()
        .orElse("");

    System.out.println("name "+ name);
    //---------------------------------------------------------------------------------
    Stream<String> language = Stream.of("java","python","node",null,"ruby",null,"php");
    List<String> result6 = language.filter(x->x!=null)
                                   .collect(Collectors.toList());
    result6.forEach(System.out::println);
    // Alternatively, filter Objects::nonNull
    // List<String> result = language.filter(Objects::nonNull).collect(Collectors.toList());
    //---------------------------------------------------------------------------------
    //Filter a Map Examples
    Map<Integer,String> map = new HashMap<>();
    map.put(1,"helin");
    map.put(2,"Selim");
    map.put(3,"ali");
    map.put(4,"veli");
    map.put(5,"ali");

    //Map -> Stream -> Filter -> String
    String result7 = map.entrySet().stream()
        .filter(x->"ali".equals(x.getValue()))
        .map(x->x.getValue())
        .collect(Collectors.joining());
    System.out.println("----> " + result7);
  }

}
