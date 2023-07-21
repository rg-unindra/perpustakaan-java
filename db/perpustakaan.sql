-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 21 Jul 2023 pada 05.22
-- Versi server: 10.1.36-MariaDB
-- Versi PHP: 7.2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `buku`
--

CREATE TABLE `buku` (
  `id_buku` varchar(20) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `pengarang` varchar(255) DEFAULT NULL,
  `penerbit` varchar(100) NOT NULL,
  `tahun` int(4) NOT NULL,
  `jumlah` int(4) NOT NULL,
  `tanggal_masuk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `judul`, `pengarang`, `penerbit`, `tahun`, `jumlah`, `tanggal_masuk`) VALUES
('2344562738', 'Pendidikan Agama Islam', 'A. Wahid', 'Armiko', 2010, 249, 0),
('283784819', 'Kewirausahaan', 'Mudie Khalia', 'Armiko', 2010, 250, 0),
('3948571678', 'Bahasa Inggris', 'Drs. Nanan Suryana', 'Armiko', 2018, 250, 0),
('9237461819', 'Pendidikan Kewarganegaraan', 'Kokom Komalasari', 'Armiko', 2017, 250, 0),
('9263745168', 'Penjualan', 'H. Ating.T', 'Armiko', 2016, 89, 0),
('9273648597', 'TIK', 'Ali Imron', 'Saka Mitra', 2017, 250, 0),
('9283741627', 'Komputer Akuntansi', 'Ali Imron', 'Armiko', 2010, 100, 0),
('9283746178', 'Paket Akutansi Biaya', NULL, 'Armiko', 2016, 100, 0),
('928374859', 'Sistem Kearsipan', 'Dra. Dewi', 'Armiko', 2017, 100, 0),
('928574637', 'Peralatan Kantor', 'Vida Hasna Farida', 'Armiko', 2010, 100, 0),
('9384756289', 'Membuka Usaha Retail', 'Dra. Henry Bouty', '', 2018, 90, 0),
('943327182', 'Penjaskes', 'Yusuf Taufik', '', 2016, 250, 0),
('9485738192', 'Bahasa Indonesia', 'Dra. Euis Honiatri', 'Armiko', 2016, 250, 0),
('9584738291', 'Akutansi Seri C', 'Drs. Hendi Somantri', 'Armiko', 2010, 100, 0),
('9786230107252', 'Data Mining', 'Muhammad Arhami|Muhammad Nasir', 'Andi', 2020, 101, 1687832376),
('9789793784922', 'Aljabar dan Kalkulus', 'Ayub Subandi', 'Rekayasa Sains', 2019, 98, 1687832376),
('9824657281', 'Ilmu Pengetahuan Alam', 'Kaila Pasha', 'Armiko', 2007, 250, 0),
('9834472819', 'Matematika', 'Drs. Maman Abdulrahman', 'Armiko', 2017, 250, 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` varchar(20) NOT NULL,
  `id_buku` varchar(1000) NOT NULL,
  `jumlah` varchar(1000) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `id_admin_pinjam` int(11) NOT NULL,
  `status` varchar(100) NOT NULL,
  `tanggal_pinjam` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `pengembalian`
--

CREATE TABLE `pengembalian` (
  `id_pengembalian` varchar(100) NOT NULL,
  `id_peminjaman` varchar(100) NOT NULL,
  `nisn` varchar(20) NOT NULL,
  `id_admin_pengembalian` int(100) NOT NULL,
  `denda` bigint(20) NOT NULL,
  `lama_pinjam` int(4) NOT NULL,
  `tanggal_pengembalian` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `login_time` bigint(20) NOT NULL COMMENT 'login time in epoch milliseconds time '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Struktur dari tabel `siswa`
--

CREATE TABLE `siswa` (
  `nisn` varchar(20) NOT NULL,
  `nama_siswa` varchar(100) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `alamat` varchar(100) NOT NULL,
  `telepon` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`nisn`, `nama_siswa`, `kelas`, `alamat`, `telepon`) VALUES
('2019434500487', 'Risky Setiawan', 'X RPL', 'Lenteng', '012831920'),
('201943500487', 'Farhan Fadila', 'XII RPL', 'Cinere', '089521127622');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `buku`
--
ALTER TABLE `buku`
  ADD PRIMARY KEY (`id_buku`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`);

--
-- Indeks untuk tabel `pengembalian`
--
ALTER TABLE `pengembalian`
  ADD PRIMARY KEY (`id_pengembalian`);

--
-- Indeks untuk tabel `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_session`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nisn`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
