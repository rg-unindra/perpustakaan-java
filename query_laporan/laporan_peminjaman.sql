SELECT p.id_peminjaman, GROUP_CONCAT(b.judul SEPARATOR ', ') AS book_titles, p.jumlah, s.nama_siswa, p.tanggal_pinjam
  FROM peminjaman p
  JOIN buku b ON FIND_IN_SET(b.id_buku, REPLACE(REPLACE(p.id_buku, ' ', ''), ',', ',')) > 0
  JOIN siswa s ON s.nisn = p.nisn
  WHERE STR_TO_DATE($P{START_DATE}, '%d/%m/%Y') >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)
  AND STR_TO_DATE($P{END_DATE}, '%d/%m/%Y') <= CURDATE()
  GROUP BY p.id_peminjaman, p.jumlah, s.nama_siswa, p.tanggal_pinjam