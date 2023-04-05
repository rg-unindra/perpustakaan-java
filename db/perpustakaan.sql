-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Apr 2023 pada 10.49
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
  `id_buku` int(11) NOT NULL,
  `id_kategori` int(11) NOT NULL,
  `id_rak` int(11) NOT NULL,
  `judul` varchar(255) NOT NULL,
  `keterangan` text,
  `isbn` varchar(255) DEFAULT NULL,
  `penerbit` varchar(255) DEFAULT NULL,
  `pengarang` varchar(255) DEFAULT NULL,
  `tahun` int(4) NOT NULL,
  `halaman` int(4) NOT NULL,
  `jumlah` int(4) NOT NULL,
  `tanggal_masuk` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `buku`
--

INSERT INTO `buku` (`id_buku`, `id_kategori`, `id_rak`, `judul`, `keterangan`, `isbn`, `penerbit`, `pengarang`, `tahun`, `halaman`, `jumlah`, `tanggal_masuk`) VALUES
(2, 1, 2, 'The Alchemist', 'Novel fiksi', '978-0062315007', 'HarperCollins', 'The Alchemist', 1988, 208, 10, 1680498829808),
(3, 2, 1, 'Sapiens: A Brief History of Humankind', 'Sejarah manusia', '978-0099590088', 'Vintage', ' Yuval Noah Harari', 2023, 512, 10, 1680499322153),
(4, 2, 1, 'The Lean Startup', 'Bisnis dan manajemen', '978-0307887894', 'Crown Business', 'Eric Ries', 2011, 336, 15, 1680499322153);

-- --------------------------------------------------------

--
-- Struktur dari tabel `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int(11) NOT NULL,
  `nama_kategori` varchar(255) NOT NULL,
  `keterangan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`, `keterangan`) VALUES
(1, 'Fiksi', 'Buku-buku yang bercerita tentang kehidupan atau pengalaman khayal edit'),
(2, 'Non-Fiksi', 'Buku-buku yang bercerita tentang kenyataan atau fakta di dunia nyata'),
(3, 'Majalah', 'Majalah dengan berbagai topik, seperti fashion, olahraga, bisnis, dan lain-lain'),
(4, 'Komik', 'Buku-buku berisi cerita bergambar dengan berbagai jenis genre');

-- --------------------------------------------------------

--
-- Struktur dari tabel `peminjaman`
--

CREATE TABLE `peminjaman` (
  `id_peminjaman` int(11) NOT NULL,
  `id_buku` int(11) NOT NULL,
  `id_siswa` int(11) NOT NULL,
  `id_admin_pinjam` int(11) NOT NULL,
  `id_admin_kembali` int(11) DEFAULT NULL,
  `status` varchar(3) NOT NULL COMMENT 'SP (Sedang Di Pinjam), TDK (Telah Di Kembalikan), BH (Buku Hilang) ',
  `tanggal_pinjam` bigint(20) NOT NULL,
  `tanggal_kembali` bigint(20) DEFAULT NULL,
  `denda` bigint(20) DEFAULT NULL,
  `max_hari` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `peminjaman`
--

INSERT INTO `peminjaman` (`id_peminjaman`, `id_buku`, `id_siswa`, `id_admin_pinjam`, `id_admin_kembali`, `status`, `tanggal_pinjam`, `tanggal_kembali`, `denda`, `max_hari`) VALUES
(3, 2, 4, 1, 1, 'TDK', 1680674760181, 1680677122144, 0, 3);

-- --------------------------------------------------------

--
-- Struktur dari tabel `rak`
--

CREATE TABLE `rak` (
  `id_rak` int(11) NOT NULL,
  `nama_rak` varchar(255) NOT NULL,
  `keterangan` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `rak`
--

INSERT INTO `rak` (`id_rak`, `nama_rak`, `keterangan`) VALUES
(1, 'Rak 1', 'edit rak netbeans 6'),
(2, 'Rak 2', 'Rak untuk buku-buku Non-Fiksi'),
(3, 'Rak 3', 'Rak untuk majalah'),
(4, 'Rak 4', 'Rak untuk komik'),
(5, 'Rak 5', 'Rak untuk buku-buku tentang sejarah dan politik'),
(6, 'Rak 6', 'Rak untuk buku-buku tentang ilmu pengetahuan dan teknologi');

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
  `id_siswa` int(11) NOT NULL,
  `nisn` varchar(255) NOT NULL,
  `nama_siswa` varchar(255) NOT NULL,
  `telepon` varchar(20) NOT NULL,
  `tahun_angkatan` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data untuk tabel `siswa`
--

INSERT INTO `siswa` (`id_siswa`, `nisn`, `nama_siswa`, `telepon`, `tahun_angkatan`) VALUES
(1, '2019435004887', 'Farhan Fadila', '089520117622', 2022),
(3, '1234567890', 'Ahmad', '081234567890', 2023),
(4, '2345678901', 'Budi', '082345678901', 2023),
(5, '3456789012', 'Cinta', '083456789012', 2023);

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
-- Indeks untuk tabel `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indeks untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  ADD PRIMARY KEY (`id_peminjaman`);

--
-- Indeks untuk tabel `rak`
--
ALTER TABLE `rak`
  ADD PRIMARY KEY (`id_rak`);

--
-- Indeks untuk tabel `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`id_session`);

--
-- Indeks untuk tabel `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`id_siswa`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `buku`
--
ALTER TABLE `buku`
  MODIFY `id_buku` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT untuk tabel `peminjaman`
--
ALTER TABLE `peminjaman`
  MODIFY `id_peminjaman` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT untuk tabel `rak`
--
ALTER TABLE `rak`
  MODIFY `id_rak` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT untuk tabel `session`
--
ALTER TABLE `session`
  MODIFY `id_session` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT untuk tabel `siswa`
--
ALTER TABLE `siswa`
  MODIFY `id_siswa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
