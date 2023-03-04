/**
 *
 *  @author Głodowski Michał S10552
 *
 */

package zad1;

//Updated source file: http://wiki.puzzlers.org/pub/wordlists/unixdict.txt
def sourceData = new URL('http://wiki.puzzlers.org/pub/wordlists/unixdict.txt')
// For anagrams' file read it line by line.
def readLineByLine = sourceData.readLines()

if (sourceData != null && readLineByLine != null) {
	// Sort all elements by their content (sub-elements they consist of). Example: [a, e, l, n, r, t]:[altern, antler, rental] ; where list of letters is a key and list of words is value.
	def data = readLineByLine.groupBy {
		// Turn each element to the list and then sort it's elements.
		it.toList().sort()
	}
	// Find out what are the sizes of map values for every key.
	def dataSizes = data.collect {
		it.value.size()
	}
	// Find all elements which sizes are equal to the biggest one.
	def getAllBiggest = data.findAll {
		//  Check every element if they are as big as the biggest one.
		it.value.size() == dataSizes.max()
	}
	// For all biggest found take only their values. 
	def extractValues = getAllBiggest.collect { 
		// Sort every list of values alphabetically and add them together with space between each sub-element.
		it.value.sort().join(" ")
		}
	// Final results will show up here, each biggest anagrams' group in separate line.
	println extractValues.join("\n")
} else {
	println "Counld not read the file! It seems that source does not contain required data to analyze. Make sure data source is correct."
}
