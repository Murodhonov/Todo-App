package Models

class User {
    var title: String = ""
    var image: Int = 0


    constructor()
    constructor(title: String, image: Int) {
        this.title = title
        this.image = image
    }
}