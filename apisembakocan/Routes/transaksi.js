const router = require('express').Router()
const transaksi = require('../controller/transaksi')

router.post('/input', (req , res) =>{

    transaksi.inputTransaksi(req.body)
        .then((result)=> res.json(result))
        .catch((err) => res.json(err))


})
router.get("/datatransaksi", (req, res)=> {
    transaksi.lihatTransaksi()
        .then((result) => res.json(result))
        .catch((err)=> res.json(err))
})
router.delete("/hapus/:id", (req, res)=> {
    transaksi.hapustransaksi(req.params.id)
        .then((result) => res.json(result))
        .catch((err)=> res.json(err))
})



module.exports = router