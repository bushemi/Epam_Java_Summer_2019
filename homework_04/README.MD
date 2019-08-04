1. Using Stream API write a method that returns a comma separated string based on a given list of integers. 
Each element should be preceded by the letter 'e' if the number is even, and preceded by the letter 'o' 
if the number is odd. 
For example, if the input list is (3,44), the output should be 'o3,e44'.

2.1. Using Stream API implement a method that produces the largest city (by population) per state
Map<String, City> getLargestCityPerState(Collection<City> cities) {
	
}

class City {
	private String state;
	private long population;
}

2.2. Implement 2.2 task using Collection API

3. Write a method public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
that alternates elements from the streams first and second, stopping when
one of them runs out of elements
