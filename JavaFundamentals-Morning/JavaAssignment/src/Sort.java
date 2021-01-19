import java.util.*;
public class Sort {
	
	public Map<Integer, Integer> sortByValue(Map<Integer, Integer> map){
		
		List<Map.Entry<Integer, Integer>> list = new LinkedList<>(map.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
			public int compare(Map.Entry<Integer, Integer> map1,Map.Entry<Integer, Integer> map2 ) {
				return (map2.getValue()).compareTo(map1.getValue());
			}
		});
		
		//Hash map is unordered so I use linked hash map to store the data in insertion order
		Map<Integer, Integer> result = new LinkedHashMap<>();
		
		for(Map.Entry<Integer, Integer> res:list ) {
			result.put(res.getKey(), res.getValue());
		}
		
		return result;
	}

}
