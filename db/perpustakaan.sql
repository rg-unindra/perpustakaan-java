-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 30 Jun 2023 pada 17.54
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
('9786230107252', 'Data Mining', 'Muhammad Arhami|Muhammad Nasir', 'Andi', 2020, 3, 1687832376),
('9789793784922', 'Aljabar dan Kalkulus', 'Ayub Subandi', 'Rekayasa Sains', 2019, 3, 1687832376);

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

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_buku`, `jumlah`, `nisn`, `id_admin_pinjam`, `status`, `tanggal_pinjam`) VALUES
('LNPBQD3JGN', '9789793784922', '1', '201943500487', 1, 'SDK', '30/06/2023'),
('P6IY5AJ8R22NFWC39RO8', '9789793784922', '1', '201943500487', 1, 'SDK', '28/06/2023'),
('XF9080JU4Z', '9789793784922,9786230107252', '2,3', '201943500487', 1, 'SDK', '30/06/2023');

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

--
-- Dumping data untuk tabel `pengembalian`
--

INSERT INTO `pengembalian` (`id_pengembalian`, `id_peminjaman`, `nisn`, `id_admin_pengembalian`, `denda`, `lama_pinjam`, `tanggal_pengembalian`) VALUES
('D8DX76Q37L', 'P6IY5AJ8R22NFWC39RO8', '201943500487', 1, 0, 2, '30/06/2023'),
('RGIOWUCJ4W', 'XF9080JU4Z', '201943500487', 1, 0, 0, '30/06/2023'),
('YVLDW2HMSX', 'LNPBQD3JGN', '201943500487', 1, 0, 0, '30/06/2023');

-- --------------------------------------------------------

--
-- Struktur dari tabel `session`
--

CREATE TABLE `session` (
  `id_session` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `login_time` bigint(20) NOT NULL COMMENT 'login time in epoch milliseconds time '
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `session`
--

INSERT INTO `session` (`id_session`, `id_user`, `login_time`) VALUES
(3, 1, 1687864402310);

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
(1, 'admin', 'admin'),
(2, 'admin2', 'admin'),
(3, 'admin4', 'admin');

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
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
