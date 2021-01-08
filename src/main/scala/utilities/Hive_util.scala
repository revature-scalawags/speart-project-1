import java.sql.ResultSet
import java.sql.Connection


class Hive_util(con: Connection) {

    val months = Map[String, String]("2020_01" -> "January", "2020_02" -> "February", "2020_03" -> "March", "2020_04" -> "April", "2020_05" -> "May", "2020_06" -> "June")

    def most_clicked_wiki_wiki(table:String){
        println(s"Most Clicked From A Wiki Page to Wiki Page For The Month Of ${months(table)}")
        println("-----------------------------------------------------------------------------")

        val query = s"""
            SELECT from_link, to_link, SUM(clicks) as csum 
            FROM $table WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            GROUP BY to_link, from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(s"SELECT from_link, to_link, SUM(clicks) as csum FROM $table WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' GROUP BY to_link, from_link ORDER BY csum DESC LIMIT 20")
        print_query(resultSet, true)
    }

    def most_clicked_anything_wiki(table:String){
        println(s"Most Clicked From A Page to Wiki Page For The Month Of ${months(table)}")
        println("------------------------------------------------------------------------")

        val query = s"""
            SELECT from_link, to_link, SUM(clicks) as csum 
            FROM $table 
            WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            GROUP BY to_link, from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(s"SELECT from_link, to_link, SUM(clicks) as csum FROM $table WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page' GROUP BY to_link, from_link ORDER BY csum DESC LIMIT 20")
        print_query(resultSet, true)
    }

    def most_clicked_wiki_link(table:String){
        println(s"Most Clicked From A Wiki Page to Wiki Page For The Month Of ${months(table)}")
        println("-----------------------------------------------------------------------------")

        val query = s"""
            SELECT to_link, SUM(clicks) as csum 
            FROM $table WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            GROUP BY to_link
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(s"SELECT from_link, to_link, SUM(clicks) as csum FROM $table WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' GROUP BY to_link, from_link ORDER BY csum DESC LIMIT 20")
        print_query(resultSet, false)
    }

    def most_clicked_wiki_page(table:String){
        println(s"Most Clicked From A Page to Wiki Page For The Month Of ${months(table)}")
        println("------------------------------------------------------------------------")

        val query = s"""
            SELECT to_link, SUM(clicks) as csum 
            FROM $table 
            WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            GROUP BY to_link
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(s"SELECT from_link, to_link, SUM(clicks) as csum FROM $table WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page' GROUP BY to_link, from_link ORDER BY csum DESC LIMIT 20")
        print_query(resultSet, false)
    }

    def most_clicked_out_wiki(table:String){
        println(s"Most Clicked Out of Wiki Page to Wiki Page For The Month Of ${months(table)}")
        println("-----------------------------------------------------------------------------")

        val query = s"""
            SELECT from_link, SUM(clicks) as csum 
            FROM $table 
            WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            GROUP BY from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(s"SELECT from_link, to_link, SUM(clicks) as csum FROM $table WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' GROUP BY to_link, from_link ORDER BY csum DESC LIMIT 20")
        print_query_from(resultSet)
    }

    def most_clicked_overall_anything_wiki(){
        println("Most Clicked From A Page to A Wiki Page Overall")
        println("-----------------------------------------------")

        val query = """
            SELECT wik.from_link, wik.to_link, SUM(wik.clicks) as csum 
            FROM (
            SELECT from_link, to_link, clicks FROM 2020_01 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_02 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_03 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_04 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_05 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_06 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            ) wik
            GROUP BY to_link, from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(query)
        
        print_query(resultSet, true)
    }


    def most_clicked_overall_wiki_wiki(){
        println("Most Clicked From Wiki A Page To A Wiki Page Link Page Overall")
        println("--------------------------------------------------------------")

        val query = """
            SELECT wik.from_link, wik.to_link, SUM(wik.clicks) as csum 
            FROM (
            SELECT from_link, to_link, clicks FROM 2020_01 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_02 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_03 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_04 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_05 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT from_link, to_link, clicks FROM 2020_06 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            ) wik
            GROUP BY to_link, from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(query)
        print_query(resultSet, true)
    }

    def most_clicked_overall_wiki_page(){
        println("Most Clicked From A Page to A Wiki Page Overall")
        println("-----------------------------------------------")

        val query = """
            SELECT wik.to_link, SUM(wik.clicks) as csum 
            FROM (
            SELECT to_link, clicks FROM 2020_01 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT to_link, clicks FROM 2020_02 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT to_link, clicks FROM 2020_03 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT to_link, clicks FROM 2020_04 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT to_link, clicks FROM 2020_05 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            UNION ALL
            SELECT to_link, clicks FROM 2020_06 WHERE NOT to_link='Main_Page' AND NOT from_link='Main_Page'
            ) wik
            GROUP BY to_link
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(query)
        
        print_query(resultSet, false)
    }


    def most_clicked_overall_wiki_link(){
        println("Most Clicked From Wiki A Page To A Wiki Page Link Page Overall")
        println("--------------------------------------------------------------")

        val query = """
            SELECT wik.to_link, SUM(wik.clicks) as csum 
            FROM (
            SELECT * FROM 2020_01 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_02 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_03 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_04 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_05 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_06 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            ) wik
            GROUP BY to_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(query)
        print_query(resultSet, false)
    }

    def most_clicked_overall_out_wiki_link(){
        println("Most Clicked Out Wiki Page To Another Wiki Page Overall")
        println("--------------------------------------------------------------")

        val query = """
            SELECT wik.from_link, SUM(wik.clicks) as csum 
            FROM (
            SELECT * FROM 2020_01 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_02 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_03 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_04 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_05 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            UNION ALL
            SELECT * FROM 2020_06 WHERE type='link' AND NOT to_link='Main_Page' AND NOT from_link='Main_Page' 
            ) wik
            GROUP BY from_link 
            ORDER BY csum DESC 
            LIMIT 20
        """

        val statement = con.createStatement()
        var resultSet = statement.executeQuery(query)
        print_query_from(resultSet)
    }

    def print_query(resultSet: ResultSet, from: Boolean){
        // if(from){
        //     println("From Link -> To Link \t\t Clicks")
        // }else{
        //     println("Wiki Page \t\t Clicks")
        // }
        
        // println("----------------------------------------")

        var x = 1

        while (resultSet.next) {
            
            val to_link = resultSet.getString("to_link")
            val csum = resultSet.getInt("csum")

            if(from){
                val from_link = resultSet.getString("from_link")
                println(x + ". " + from_link + " to " + to_link + "\t\t" + csum)
            }else{
                println(x + ". " + to_link + "\t\t" + csum)
            }

            x += 1
            
        }
    }

    def print_query_from(resultSet: ResultSet){
        // if(from){
        //     println("From Link -> To Link \t\t Clicks")
        // }else{
        //     println("Wiki Page \t\t Clicks")
        // }
        
        // println("----------------------------------------")

        var x = 1

        while (resultSet.next) {
            
            val from_link = resultSet.getString("from_link")
            val csum = resultSet.getInt("csum")
            println(x + ". " + from_link + "\t\t" + csum)

            x += 1
            
        }
    }

}