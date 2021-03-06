const userModel = require('../Model/user.js')
const response = require('../Config/reponse')
const bcrypt = require('bcrypt')



exports.registrasi = (data) =>
    new Promise((resolve, reject) => {

        userModel.findOne({username: data.username})
            .then(user => {
                if(user){
                    resolve(response.commonErrorMsg('Username Sudah di Gunakan'))
                } else{
                    bcrypt.hash(data.password, 10, (err, hash)=> {
                        if(err) {
                            reject(response.commonErrorMsg)
                        } else{
                            data.password = hash
                            userModel.create(data)
                                .then(()=> resolve (response.commonSuccessMsg('Berhasil Registrasi')))
                                .catch(() => reject(response.commonErrorMsg('Mohon Maaf, Registrasi Gagal')))

                        }

                    })
                }
        }).catch(()=> reject(response.commonError()))



    })
exports.lihatDataCustomer = () =>
    new Promise(async (resolve, reject) => {
        userModel.find({})
            .then(result => {
                resolve(response.commonresult(result))
            })
            .catch(() =>  reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami')))
    })

exports.login = (data) =>
    new Promise((resolve, reject) => {

        userModel.findOne({
            username: data.username
        }) .then(user => {
            if (user){
                if (bcrypt.compareSync(data.password, user.password)){
                    resolve(response.commonresult(user))
                }else{
                    reject(response.commonErrorMsg('Password Salah'))
                }
            }else {
                reject(response.commonErrorMsg('Username Tidak DiTemukan'))

            }
        })
    })

