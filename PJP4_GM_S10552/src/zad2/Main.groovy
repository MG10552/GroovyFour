/**
 *
 *  @author Głodowski Michał S10552
 *
 */

package zad2;

// Choose initial file with customers.
def sourceData = new File("src/zad2/customers.txt")
// For customers' file read it line by line.
def readLbyL = sourceData.readLines()

if (sourceData != null && readLbyL != null) {
	def data = readLbyL.collect {
		// Create elements on each separator.
		it.split(";")
	}
	// Result for sorting clients by their full names.
	println "\nNazwiska"
	// Sort by full names and if two or more clients have the same name sort by their client ID.
	def sortByNames = data.sort { a, b -> a[1] <=> b[1] ?: a[0] <=> b[0] }
	// Gather results and prepare data for print.
	def namesResult = sortByNames.collect { it.flatten().join(";") }
	// Print each result in new line as required.
	println namesResult.join("\n")
	
	// Result for sorting clients by costs of their orders.
	println "\nKoszty"
	// Process data taking costs of orders into account.
	def cost = data.collect { 
			// Calculate cost of every order. Then add cost to order and return both together.
			def v = ((it[3].toDouble()) * (it[4].toDouble())).round(2)
			return (it + v)
		}
		// Sort by costs of orders (descending) and if two orders have the same value sort them by clients' IDs (ascending)
		def sortByCosts = cost.sort{ c, d -> d[5] <=> c[5] ?: c[0] <=> d[0] }
		// Gather results and prepare data for print according to the requirement of having cost prefixed.
		def costsResult = sortByCosts.collect {
					it[0..4].flatten().join(";") + " (Koszt: " +  it[5] + ")"
					}
		// Print each result in new line as required.			
		println costsResult.join("\n")
		
		// Result for client #0001's orders.		
		println "\nKlient c00001"
		// Search customers for ones with ID 'c00001'. 
		c1 = sortByNames.findAll() {
			it =~ /c00001/
		}
		// Gather results and prepare data for print.
		def c1Result = c1.collect { it.flatten().join(";") }
		// Print each result in new line.
		println c1Result.join("\n")
		
		// Result for client #0002's orders.
		println "\nKlient c00002"
		// Search customers for ones with ID 'c00002'.
		c2 = sortByNames.findAll() {
			it =~ /c00002/
		}
		// Gather results and prepare data for print.
		def c2Result = c2.collect { it.flatten().join(";") }
		// Print each result in new line.
		println c2Result.join("\n")
} else {
	println "Counld not read the file! It seems that source does not contain required data to analyze. Make sure data source is correct."
}
