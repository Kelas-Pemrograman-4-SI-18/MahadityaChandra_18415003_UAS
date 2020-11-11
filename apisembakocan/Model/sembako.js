const mongoose= require('mongoose');

const userSchema = mongoose.Schema({
    kodesembako : {
        type : String
    },
    namaSembako : {
        type : String
    },
    merkSembako : {
        type : String
    },
    hargaSembako : {
        type : String
    },
    satuanSembako : {
        type : String
    },
    gambar : {
        type : String
    }



})

module.exports = mongoose.model('sembako', userSchema)
