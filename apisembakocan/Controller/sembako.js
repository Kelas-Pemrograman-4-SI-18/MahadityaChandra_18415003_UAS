const sembako = require('../model/sembako.js')
const response = require('../Config/reponse')
const mongoose = require('mongoose')
const ObjectId = mongoose.Types.ObjectId

exports.inputDataSembako = (data, gambar) =>
    new Promise(async (resolve, reject) => {

        const sembakoBaru = new sembako({
            kodesembako: data.kodesembako,
            namaSembako : data.namaSembako,
            merkSembako : data.merkSembako,
            hargaSembako : data.hargaSembako,
            satuanSembako :data.satuanSembako,
            gambar : gambar

        })
        await sembako.findOne({kodesembako: data.kodesembako})
            .then(sembako => {
                if(sembako){
                    reject(response.commonErrorMsg('Kode Sembako Sudah digunakan'))
                }else{
                    sembakoBaru.save()
                        .then(r=>{
                            resolve(response.commonSuccessMsg('Berhasil Input Data'))
                        }).catch(err =>{
                        reject(response.commonErrorMsg('Mohon Maaf, Input Data Gagal'))
                    })
                }
            }).catch(err => {
                reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami'))
            })
    })

exports.lihatDataSembako = () =>
    new Promise(async (resolve, reject) => {
        await sembako.find({})
            .then(result => {
                resolve(response.commonresult(result))
            })
            .catch(() =>  reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami')))

    })
exports.lihatDetailDataSembako = (kodesembako) =>
    new Promise(async (resolve, reject) => {
        await sembako.findOne({kodesembako: kodesembako})
            .then(result => {
                resolve(response.commonresult(result))
            })
            .catch(() =>  reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami')))

    })

exports.updateSembako = (id, data, gambar) =>
    new Promise(async (resolve, reject) => {
        await sembako.updateOne(
            {_id: ObjectId(id)},
            {
                $set: {
                    kodesembako: data.kodesembako,
                    namaSembako : data.namaSembako,
                    merkSembako : data.merkSembako,
                    hargaSembako : data.hargaSembako,
                    satuanSembako :data.satuanSembako,
                    gambar : gambar
                }
            }
        ).then(sembako => {
            resolve(response.commonSuccessMsg('Berhasil mengubah Data'))
        }).catch( err => {
            reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami'))
        })
    })

exports.hapussembako = (_id) =>
    new Promise(async (resolve, reject) => {
        await sembako.remove({_id:ObjectId(_id)})
            .then(() => {
                resolve(response.commonSuccessMsg('Berhasil menghapus Data'))
            }).catch(() => {
                reject(response.commonErrorMsg('Mohon Maaf, Terjadi Kesalahan pada Server Kami'))
            })

    })
