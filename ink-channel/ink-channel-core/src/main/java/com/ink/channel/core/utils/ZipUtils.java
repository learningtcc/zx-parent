/**
 * 
 */
package com.ink.channel.core.utils;

/**
 * 文本压缩类
 * 功能模块：基层工具类
 * Copyright: Copyright (c) 2015 
 * Create Date: 2015年1月15日
 * Company: www.minshengec.com 
 * @author luxueqiang@minshengec.com
 * @version $Id: ZipUtils.java,v 1.0 Eric.Lu Exp $
 */
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bill99.seashell.common.util.Base64Util;

public class ZipUtils {
	private static final Log log = LogFactory.getLog(ZipUtils.class);
	
	
	public static void gzipfile( String srcFile, String destFile ){
		try {
			// 打开需压缩文件作为文件输入流
			FileInputStream fin = new FileInputStream(srcFile);
			// 建立压缩文件输出流
			FileOutputStream fout = new FileOutputStream(destFile);
			// 建立gzip压缩输出流
			GZIPOutputStream gzout = new GZIPOutputStream(fout);
			// 设定读入缓冲区尺寸
			byte[] buf = new byte[1024];
			int num;
			while ((num = fin.read(buf)) != -1) {
				gzout.write(buf, 0, num);
			}

			gzout.close();
			fout.close();
			fin.close();
		} catch (Exception ex) {
			log.error("gzip压缩文件异常", ex);
			//throw new AppException("E999999999", "gzip压缩文件异常");
		}
	}
	
	
	/**
	 * 
	 * 使用gzip进行压缩
	 */
	public static String gzip(String primStr) {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		GZIPOutputStream gzip = null;
		try {
			gzip = new GZIPOutputStream(out);
			gzip.write(primStr.getBytes("UTF-8"));
		} catch (IOException e) {
			log.error("数据压缩异常",e);
			//throw new AppException("E999999999", "gzip数据压缩异常");
		} finally {
			if (gzip != null) {
				try {
					gzip.close();
				} catch (IOException e) {
					log.error("压缩关闭异常",e);
				}
			}
		}

		return new String(Base64Util.encode(out.toByteArray()));
	}
	/**
	 * 使用gzip进行解压缩
	 * @param compressedStr
	 * @return GZIPInputStream
	 */
	public static GZIPInputStream gunzipInputStream(String compressedStr) {
		if (StringUtils.isBlank(compressedStr)) {
			return null;
		}
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		try {
			compressed = Base64Util.decode( compressedStr.getBytes() );
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

		} catch (IOException e) {
			log.error("数据解压异常",e);
			//throw new AppException("E999999999", "数据解压异常");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
		return ginzip;
	}
	/**
	 *
	 * <p>
	 * Description:使用gzip进行解压缩
	 * </p>
	 * 
	 * @param compressedStr
	 * @return
	 */
	public static String gunzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ByteArrayInputStream in = null;
		GZIPInputStream ginzip = null;
		byte[] compressed = null;
		String decompressed = null;
		try {
			compressed = Base64Util.decode( compressedStr.getBytes() );
			in = new ByteArrayInputStream(compressed);
			ginzip = new GZIPInputStream(in);

			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = ginzip.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			log.error("数据解压异常",e);
			//throw new AppException("E999999999", "数据解压异常");
		} finally {
			if (ginzip != null) {
				try {
					ginzip.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}

		return decompressed;
	}
	/**
	 * 
	 * @param compressedStr
	 * @param desFile
	 */
	public static void gunzip(String compressedStr,String desFile) {
        if (StringUtils.isEmpty(compressedStr)) {
            throw new RuntimeException("compressedStr can not be empty!");
        }
        if (StringUtils.isEmpty(desFile)) {
            throw new RuntimeException("desFile can not be empty!");
        }
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        FileOutputStream outFile=null;
        byte[] compressed = null;
        try {
            outFile=new FileOutputStream(new File(desFile));
            compressed = Base64Util.decode( compressedStr.getBytes() );
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                outFile.write(buffer, 0, offset);
            }
        } catch (IOException e) {
            log.error("数据解压异常",e);
            //throw new AppException("E999999999", "数据解压异常");
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (outFile != null) {
                try {
                    outFile.close();
                } catch (IOException e) {
                }
            }
        }

    }
	/**
	 * 使用zip进行压缩
	 * 
	 * @param str
	 *            压缩前的文本
	 * @return 返回压缩后的文本
	 */
	public static final String zip(String str) {
		if (str == null)
			return null;
		byte[] compressed;
		ByteArrayOutputStream out = null;
		ZipOutputStream zout = null;
		String compressedStr = null;
		try {
			out = new ByteArrayOutputStream();
			zout = new ZipOutputStream(out);
			zout.putNextEntry(new ZipEntry("0"));
			zout.write(str.getBytes());
			zout.closeEntry();
			compressed = out.toByteArray();
			compressedStr = new String(Base64Util.encode(compressed));
		} catch (IOException e) {
			compressed = null;
		}
		finally {
			if (zout != null) {
				try {
					zout.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return compressedStr;
	}

	/**
	 * 使用zip进行解压缩
	 * 
	 * @param compressed
	 *            压缩后的文本
	 * @return 解压后的字符串
	 */
	public static final String unzip(String compressedStr) {
		if (compressedStr == null) {
			return null;
		}

		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		ZipInputStream zin = null;
		String decompressed = null;
		try {
			byte[] compressed = Base64Util.decode(compressedStr.getBytes("UTF-8"));
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			zin = new ZipInputStream(in);
			zin.getNextEntry();
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = zin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString();
		} catch (IOException e) {
			decompressed = null;
		} catch (Exception e) {
			log.error("发生异常",e);
			decompressed = null;
		} finally {
			if (zin != null) {
				try {
					zin.close();
				} catch (IOException e) {
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
				}
			}
		}
		return decompressed;
	}
}
