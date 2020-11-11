const mongoose= require('mongoose');

const userSchema = mongoose.Schema({
    username : {
        type : String
    },
    kodesembako1 : {
        type : String
    },
    jumlahbeli1 : {
        type : String
    },
    kodesembako2 : {
        type : String
    },
    jumlahbeli2 : {
        type : String
    },
    kodesembako3 : {
        type : String
    },
    jumlahbeli3: {
        type : String
    },
    alamat : {
        type : String
    },
    noTelppenerima : {
        type : String
    }



})

module.exports = mongoose.model('transaksi', userSchema)
