package Models

class FromTask4 {

    var todoName = ""
    var todoDescription = ""
    var todoDegree = 0
    var todoCreation = ""
    var todoDedline = ""
    var todoFolders = ""

    constructor()
    constructor(
        todoName: String,
        todoDescription: String,
        todoDegree: Int,
        todoCreation: String,
        todoDedline: String,
        todoFolders: String
    ) {
        this.todoName = todoName
        this.todoDescription = todoDescription
        this.todoDegree = todoDegree
        this.todoCreation = todoCreation
        this.todoDedline = todoDedline
        this.todoFolders = todoFolders
    }

}