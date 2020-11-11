module.exports = {

    commonError: {
        error : true,
        msg : 'Terjadi Kesalahan pada Server'
    },
    commonErrorMsg: (msg) => {
        return {
            error : true,
            msg : msg
        }
    },
    commonSuccess : {
        error : false,
        msg : 'Berhasil Memuat Permintaan'
    },
    commonSuccessMsg: (msg) => {
        return {
            error : false,
            msg : msg
        }
    },
    commonresult: (data) => {
        return {
            error : false,
            msg : 'Berhasil Memuat Permintaan',
            data : data
        }

    }



}