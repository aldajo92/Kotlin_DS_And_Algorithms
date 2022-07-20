fun minWindow(s: String, t: String): String {
    val sSize = s.length
    val tSize = t.length
    
    if(sSize == 0 || tSize == 0) return ""
    
    val tMap = mutableMapOf<Char, Int>()
    t.forEach{ char ->
        tMap[char] = tMap.getOrDefault(char, 0) + 1
    }
    
    val required = tMap.size
    var left = 0
    var right = 0
    var formed = 0
    
    val windowCounts = mutableMapOf<Char, Int>()
    val ans = intArrayOf(-1,0,0)
    
    while(right < sSize){
        var char : Char = s[right]
        val count = windowCounts.getOrDefault(char, 0)
        windowCounts[char] = count + 1
        
        if(tMap.containsKey(char) && windowCounts[char] == tMap[char]){
            formed = formed + 1
        }
        
        while(left <= right && formed == required){
            char = s[left]
            if (ans[0] == -1 || right - left + 1 < ans[0]) {
                ans[0] = right - left + 1
                ans[1] = left
                ans[2] = right
            }
            
            windowCounts[char] = windowCounts.getOrDefault(char, 0) - 1
            if(tMap.containsKey(char) && windowCounts[char]!! < tMap[char]!!){
                formed = formed - 1
            }
            left = left + 1
        }
        right = right + 1
    }
    
    return if(ans[0] == -1) "" else s.subSequence(ans[1], ans[2]+1).toString()
}

fun main(){
    println(
        minWindow("ADOBECODEBANC", "ABC")
    )
}
