The project uses pom.xml only for dependency management.
Please add your input as a new line inside in/input.txt file of the project
To execute the examples inside in/input.txt file, please run the following command:

mvn clean compile
Add input to the in/input.txt in a new line
Please run the class ZipCodeChallengeTest from the IDE.

The class ZipCodeChallengeTest will execute several test zip code ranges in in/input.txt file.

It should produce following results:

([94133, 94133], [94200, 94299],[94600, 94699])
Merged Result: [94133, 94133], [94200, 94299], [94600, 94699]
([94133, 94133],[94200, 94299], [94226, 94399])
Merged Result: [94133, 94133], [94200, 94399]
([49679, 52015], [49800, 50000], [51500, 53479], [45012, 46937], [54012, 59607], [45500, 45590], [45999, 47900], [44000, 45000], [43012, 45950])
Merged Result: [43012, 47900], [49679, 53479], [54012, 59607]
([10000, 20000], [20001, 30000])
Merged Result: [10000, 30000]


Overview of the program:

1) First the list of zip code ranges are sorted using insertion sort. I have used the insertion sort consciously. 
Although insertion sort has a theoretical complexity of O(n^2), it works better than merge sort of complexity O(n lg n) due to larger constants involved in the merge sort.
2) The optimizeZipCodeRanges method will insert first zip code range as it is from the sorted list into a brand new list.
Now it starts checking each subsequent zip code range whether it is contiguous with the last zip code range inserted into the new list.
i) If it is, then the TO value of the last zip code range added to new list is changed to the current zip code TO value. 
The current zip code range is NOT added to the new list.
ii) If it is not, then program adds the current zip code range as it is to the new list.
The program runs steps i) and ii) until the sorted list is completely traversed.