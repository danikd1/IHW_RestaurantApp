package Entities

class Review (val user : User, val rating : Int, val text : String){
    override fun toString() : String {
        return "${user.userName},${rating},${text}"
    }
}