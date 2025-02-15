package jagex2.graphics;

import deob.ObfuscatedName;
import jagex2.io.Jagfile;

@ObfuscatedName("gb")
public class Pix3D extends Pix2D {

	@ObfuscatedName("gb.v")
	public static boolean field544;

	@ObfuscatedName("gb.w")
	public static int field545 = 787;

	@ObfuscatedName("gb.x")
	public static boolean field546;

	@ObfuscatedName("gb.y")
	public static int field547 = 473;

	@ObfuscatedName("gb.z")
	public static boolean field548 = true;

	@ObfuscatedName("gb.A")
	public static boolean lowDetail = true;

	@ObfuscatedName("gb.D")
	public static boolean jagged = true;

	@ObfuscatedName("gb.H")
	public static int[] divTable = new int[512];

	@ObfuscatedName("gb.I")
	public static int[] divTable2 = new int[2048];

	@ObfuscatedName("gb.J")
	public static int[] sinTable = new int[2048];

	@ObfuscatedName("gb.K")
	public static int[] cosTable = new int[2048];

	@ObfuscatedName("gb.N")
	public static Pix8[] textures;

	@ObfuscatedName("gb.O")
	public static boolean[] textureTranslucent;

	@ObfuscatedName("gb.P")
	public static int[] averageTextureRGB;

	@ObfuscatedName("gb.S")
	public static int[][] activeTexels;

	@ObfuscatedName("gb.T")
	public static int[] textureCycle;

	@ObfuscatedName("gb.V")
	public static int[] colourTable;

	@ObfuscatedName("gb.W")
	public static int[][] texturePalette;

	@ObfuscatedName("gb.E")
	public static int trans;

	@ObfuscatedName("gb.F")
	public static int centerW3D;

	@ObfuscatedName("gb.G")
	public static int centerH3D;

	@ObfuscatedName("gb.M")
	public static int textureCount;

	@ObfuscatedName("gb.Q")
	public static int poolSize;

	@ObfuscatedName("gb.U")
	public static int cycle;

	@ObfuscatedName("gb.B")
	public static boolean hclip;

	@ObfuscatedName("gb.C")
	public static boolean opaque;

	@ObfuscatedName("gb.L")
	public static int[] lineOffset;

	@ObfuscatedName("gb.R")
	public static int[][] texelPool;

	@ObfuscatedName("gb.a(Z)V")
	public static final void unload(boolean arg0) {
		divTable = null;
		divTable = null;
		sinTable = null;
		cosTable = null;
		lineOffset = null;
		textures = null;
		textureTranslucent = null;
		averageTextureRGB = null;
		if (!arg0) {
			field548 = !field548;
		}
		texelPool = null;
		activeTexels = null;
		textureCycle = null;
		colourTable = null;
		texturePalette = null;
	}

	@ObfuscatedName("gb.c(I)V")
	public static final void init2D(int arg0) {
		label25: while (true) {
			int var1;
			if (arg0 >= 0) {
				var1 = 1;
				while (true) {
					if (var1 <= 0) {
						continue label25;
					}
					var1++;
				}
			}
			lineOffset = new int[Pix2D.height2d];
			for (var1 = 0; var1 < Pix2D.height2d; var1++) {
				lineOffset[var1] = Pix2D.width2d * var1;
			}
			centerW3D = Pix2D.width2d / 2;
			centerH3D = Pix2D.height2d / 2;
			return;
		}
	}

	@ObfuscatedName("gb.a(III)V")
	public static final void init3D(int arg0, int arg1, int arg2) {
		if (arg2 != 0) {
			field544 = !field544;
		}
		lineOffset = new int[arg0];
		for (int var3 = 0; var3 < arg0; var3++) {
			lineOffset[var3] = arg1 * var3;
		}
		centerW3D = arg1 / 2;
		centerH3D = arg0 / 2;
	}

	@ObfuscatedName("gb.b(Z)V")
	public static final void clearTexels(boolean arg0) {
		if (!arg0) {
			texelPool = null;
			for (int var1 = 0; var1 < 50; var1++) {
				activeTexels[var1] = null;
			}
		}
	}

	@ObfuscatedName("gb.a(II)V")
	public static final void initPool(int arg0, int arg1) {
		label34: while (true) {
			int var2;
			if (arg1 >= 0) {
				var2 = 1;
				while (true) {
					if (var2 <= 0) {
						continue label34;
					}
					var2++;
				}
			}
			if (texelPool != null) {
				return;
			}
			poolSize = arg0;
			if (lowDetail) {
				texelPool = new int[poolSize][16384];
			} else {
				texelPool = new int[poolSize][65536];
			}
			for (var2 = 0; var2 < 50; var2++) {
				activeTexels[var2] = null;
			}
			return;
		}
	}

	@ObfuscatedName("gb.a(BLub;)V")
	public static final void unpackTextures(byte arg0, Jagfile arg1) {
		if (arg0 != 2) {
			return;
		}
		textureCount = 0;
		for (int var2 = 0; var2 < 50; var2++) {
			try {
				textures[var2] = new Pix8(arg1, String.valueOf(var2), 0);
				if (lowDetail && textures[var2].cropW == 128) {
					textures[var2].shrink(field546);
				} else {
					textures[var2].crop(0);
				}
				textureCount++;
			} catch (Exception var3) {
			}
		}
	}

	@ObfuscatedName("gb.b(II)I")
	public static final int getAverageTextureRGB(int arg0, int arg1) {
		int var8 = 25 / arg0;
		if (averageTextureRGB[arg1] != 0) {
			return averageTextureRGB[arg1];
		}
		int var2 = 0;
		int var3 = 0;
		int var4 = 0;
		int var5 = texturePalette[arg1].length;
		for (int var6 = 0; var6 < var5; var6++) {
			var2 += texturePalette[arg1][var6] >> 16 & 0xFF;
			var3 += texturePalette[arg1][var6] >> 8 & 0xFF;
			var4 += texturePalette[arg1][var6] & 0xFF;
		}
		int var7 = (var2 / var5 << 16) + (var3 / var5 << 8) + var4 / var5;
		var7 = setGamma(var7, 1.4D);
		if (var7 == 0) {
			var7 = 1;
		}
		averageTextureRGB[arg1] = var7;
		return var7;
	}

	@ObfuscatedName("gb.c(II)V")
	public static final void pushTexture(int arg0, int arg1) {
		if (activeTexels[arg0] != null) {
			texelPool[poolSize++] = activeTexels[arg0];
			int var2 = 11 / arg1;
			activeTexels[arg0] = null;
		}
	}

	@ObfuscatedName("gb.d(I)[I")
	public static final int[] getTexels(int arg0) {
		textureCycle[arg0] = cycle++;
		if (activeTexels[arg0] != null) {
			return activeTexels[arg0];
		}
		int[] var1;
		int var4;
		if (poolSize > 0) {
			var1 = texelPool[--poolSize];
			texelPool[poolSize] = null;
		} else {
			int var2 = 0;
			int var3 = -1;
			for (var4 = 0; var4 < textureCount; var4++) {
				if (activeTexels[var4] != null && (textureCycle[var4] < var2 || var3 == -1)) {
					var2 = textureCycle[var4];
					var3 = var4;
				}
			}
			var1 = activeTexels[var3];
			activeTexels[var3] = null;
		}
		activeTexels[arg0] = var1;
		Pix8 var6 = textures[arg0];
		int[] var7 = texturePalette[arg0];
		int var5;
		if (lowDetail) {
			textureTranslucent[arg0] = false;
			for (var4 = 0; var4 < 4096; var4++) {
				var5 = var1[var4] = var7[var6.pixels[var4]] & 0xF8F8FF;
				if (var5 == 0) {
					textureTranslucent[arg0] = true;
				}
				var1[var4 + 4096] = var5 - (var5 >>> 3) & 0xF8F8FF;
				var1[var4 + 8192] = var5 - (var5 >>> 2) & 0xF8F8FF;
				var1[var4 + 12288] = var5 - (var5 >>> 2) - (var5 >>> 3) & 0xF8F8FF;
			}
		} else {
			if (var6.width == 64) {
				for (var4 = 0; var4 < 128; var4++) {
					for (var5 = 0; var5 < 128; var5++) {
						var1[var5 + (var4 << 7)] = var7[var6.pixels[(var5 >> 1) + (var4 >> 1 << 6)]];
					}
				}
			} else {
				for (var4 = 0; var4 < 16384; var4++) {
					var1[var4] = var7[var6.pixels[var4]];
				}
			}
			textureTranslucent[arg0] = false;
			for (var4 = 0; var4 < 16384; var4++) {
				var1[var4] &= 0xF8F8FF;
				var5 = var1[var4];
				if (var5 == 0) {
					textureTranslucent[arg0] = true;
				}
				var1[var4 + 16384] = var5 - (var5 >>> 3) & 0xF8F8FF;
				var1[var4 + 32768] = var5 - (var5 >>> 2) & 0xF8F8FF;
				var1[var4 + 49152] = var5 - (var5 >>> 2) - (var5 >>> 3) & 0xF8F8FF;
			}
		}
		return var1;
	}

	@ObfuscatedName("gb.a(ZD)V")
	public static final void setBrightness(boolean arg0, double arg1) {
		double var28 = arg1 + (Math.random() * 0.03D - 0.015D);
		int var3 = 0;
		for (int var4 = 0; var4 < 512; var4++) {
			double var5 = (double) (var4 / 8) / 64.0D + 0.0078125D;
			double var7 = (double) (var4 & 0x7) / 8.0D + 0.0625D;
			for (int var9 = 0; var9 < 128; var9++) {
				double var10 = (double) var9 / 128.0D;
				double var12 = var10;
				double var14 = var10;
				double var16 = var10;
				if (var7 != 0.0D) {
					double var18;
					if (var10 < 0.5D) {
						var18 = var10 * (var7 + 1.0D);
					} else {
						var18 = var10 + var7 - var10 * var7;
					}
					double var20 = var10 * 2.0D - var18;
					double var22 = var5 + 0.3333333333333333D;
					if (var22 > 1.0D) {
						var22--;
					}
					double var26 = var5 - 0.3333333333333333D;
					if (var26 < 0.0D) {
						var26++;
					}
					if (var22 * 6.0D < 1.0D) {
						var12 = var20 + (var18 - var20) * 6.0D * var22;
					} else if (var22 * 2.0D < 1.0D) {
						var12 = var18;
					} else if (var22 * 3.0D < 2.0D) {
						var12 = var20 + (var18 - var20) * (0.6666666666666666D - var22) * 6.0D;
					} else {
						var12 = var20;
					}
					if (var5 * 6.0D < 1.0D) {
						var14 = var20 + (var18 - var20) * 6.0D * var5;
					} else if (var5 * 2.0D < 1.0D) {
						var14 = var18;
					} else if (var5 * 3.0D < 2.0D) {
						var14 = var20 + (var18 - var20) * (0.6666666666666666D - var5) * 6.0D;
					} else {
						var14 = var20;
					}
					if (var26 * 6.0D < 1.0D) {
						var16 = var20 + (var18 - var20) * 6.0D * var26;
					} else if (var26 * 2.0D < 1.0D) {
						var16 = var18;
					} else if (var26 * 3.0D < 2.0D) {
						var16 = var20 + (var18 - var20) * (0.6666666666666666D - var26) * 6.0D;
					} else {
						var16 = var20;
					}
				}
				int var32 = (int) (var12 * 256.0D);
				int var19 = (int) (var14 * 256.0D);
				int var33 = (int) (var16 * 256.0D);
				int var21 = (var32 << 16) + (var19 << 8) + var33;
				int var34 = setGamma(var21, var28);
				colourTable[var3++] = var34;
			}
		}
		for (int var29 = 0; var29 < 50; var29++) {
			if (textures[var29] != null) {
				int[] var6 = textures[var29].palette;
				texturePalette[var29] = new int[var6.length];
				for (int var31 = 0; var31 < var6.length; var31++) {
					texturePalette[var29][var31] = setGamma(var6[var31], var28);
				}
			}
		}
		if (!arg0) {
			field547 = -352;
		}
		for (int var30 = 0; var30 < 50; var30++) {
			pushTexture(var30, 150);
		}
	}

	@ObfuscatedName("gb.a(ID)I")
	public static int setGamma(int arg0, double arg1) {
		double var3 = (double) (arg0 >> 16) / 256.0D;
		double var5 = (double) (arg0 >> 8 & 0xFF) / 256.0D;
		double var7 = (double) (arg0 & 0xFF) / 256.0D;
		double var12 = Math.pow(var3, arg1);
		double var13 = Math.pow(var5, arg1);
		double var14 = Math.pow(var7, arg1);
		int var9 = (int) (var12 * 256.0D);
		int var10 = (int) (var13 * 256.0D);
		int var11 = (int) (var14 * 256.0D);
		return (var9 << 16) + (var10 << 8) + var11;
	}

	@ObfuscatedName("gb.a(IIIIIIIII)V")
	public static final void gouraudTriangle(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8) {
		int var9 = 0;
		int var10 = 0;
		if (arg1 != arg0) {
			var9 = (arg4 - arg3 << 16) / (arg1 - arg0);
			var10 = (arg7 - arg6 << 15) / (arg1 - arg0);
		}
		int var11 = 0;
		int var12 = 0;
		if (arg2 != arg1) {
			var11 = (arg5 - arg4 << 16) / (arg2 - arg1);
			var12 = (arg8 - arg7 << 15) / (arg2 - arg1);
		}
		int var13 = 0;
		int var14 = 0;
		if (arg2 != arg0) {
			var13 = (arg3 - arg5 << 16) / (arg0 - arg2);
			var14 = (arg6 - arg8 << 15) / (arg0 - arg2);
		}
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Pix2D.boundBottom) {
				if (arg1 > Pix2D.boundBottom) {
					arg1 = Pix2D.boundBottom;
				}
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					arg8 = arg6 <<= 0xF;
					if (arg0 < 0) {
						arg5 -= var13 * arg0;
						arg3 -= var9 * arg0;
						arg8 -= var14 * arg0;
						arg6 -= var10 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					arg7 <<= 0xF;
					if (arg1 < 0) {
						arg4 -= var11 * arg1;
						arg7 -= var12 * arg1;
						arg1 = 0;
					}
					if (arg0 != arg1 && var13 < var9 || arg0 == arg1 && var13 > var11) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg0, 0, 0, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
									arg5 += var13;
									arg4 += var11;
									arg8 += var14;
									arg7 += var12;
									arg0 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg0, 0, 0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
							arg5 += var13;
							arg3 += var9;
							arg8 += var14;
							arg6 += var10;
							arg0 += Pix2D.width2d;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg0, 0, 0, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
									arg5 += var13;
									arg4 += var11;
									arg8 += var14;
									arg7 += var12;
									arg0 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg0, 0, 0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
							arg5 += var13;
							arg3 += var9;
							arg8 += var14;
							arg6 += var10;
							arg0 += Pix2D.width2d;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					arg7 = arg6 <<= 0xF;
					if (arg0 < 0) {
						arg4 -= var13 * arg0;
						arg3 -= var9 * arg0;
						arg7 -= var14 * arg0;
						arg6 -= var10 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0xF;
					if (arg2 < 0) {
						arg5 -= var11 * arg2;
						arg8 -= var12 * arg2;
						arg2 = 0;
					}
					if (arg0 != arg2 && var13 < var9 || arg0 == arg2 && var11 > var9) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg0, 0, 0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
									arg5 += var11;
									arg3 += var9;
									arg8 += var12;
									arg6 += var10;
									arg0 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg0, 0, 0, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
							arg4 += var13;
							arg3 += var9;
							arg7 += var14;
							arg6 += var10;
							arg0 += Pix2D.width2d;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg0, 0, 0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
									arg5 += var11;
									arg3 += var9;
									arg8 += var12;
									arg6 += var10;
									arg0 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg0, 0, 0, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
							arg4 += var13;
							arg3 += var9;
							arg7 += var14;
							arg6 += var10;
							arg0 += Pix2D.width2d;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Pix2D.boundBottom) {
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg0 > Pix2D.boundBottom) {
					arg0 = Pix2D.boundBottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					arg6 = arg7 <<= 0xF;
					if (arg1 < 0) {
						arg3 -= var9 * arg1;
						arg4 -= var11 * arg1;
						arg6 -= var10 * arg1;
						arg7 -= var12 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0xF;
					if (arg2 < 0) {
						arg5 -= var13 * arg2;
						arg8 -= var14 * arg2;
						arg2 = 0;
					}
					if (arg1 != arg2 && var9 < var11 || arg1 == arg2 && var9 > var13) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg1, 0, 0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
									arg3 += var9;
									arg5 += var13;
									arg6 += var10;
									arg8 += var14;
									arg1 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg1, 0, 0, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
							arg3 += var9;
							arg4 += var11;
							arg6 += var10;
							arg7 += var12;
							arg1 += Pix2D.width2d;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg1, 0, 0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
									arg3 += var9;
									arg5 += var13;
									arg6 += var10;
									arg8 += var14;
									arg1 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg1, 0, 0, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
							arg3 += var9;
							arg4 += var11;
							arg6 += var10;
							arg7 += var12;
							arg1 += Pix2D.width2d;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					arg8 = arg7 <<= 0xF;
					if (arg1 < 0) {
						arg5 -= var9 * arg1;
						arg4 -= var11 * arg1;
						arg8 -= var10 * arg1;
						arg7 -= var12 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					arg6 <<= 0xF;
					if (arg0 < 0) {
						arg3 -= var13 * arg0;
						arg6 -= var14 * arg0;
						arg0 = 0;
					}
					if (var9 < var11) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg1, 0, 0, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
									arg3 += var13;
									arg4 += var11;
									arg6 += var14;
									arg7 += var12;
									arg1 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg1, 0, 0, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
							arg5 += var9;
							arg4 += var11;
							arg8 += var10;
							arg7 += var12;
							arg1 += Pix2D.width2d;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									gouraudRaster(Pix2D.data, arg1, 0, 0, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
									arg3 += var13;
									arg4 += var11;
									arg6 += var14;
									arg7 += var12;
									arg1 += Pix2D.width2d;
								}
							}
							gouraudRaster(Pix2D.data, arg1, 0, 0, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
							arg5 += var9;
							arg4 += var11;
							arg8 += var10;
							arg7 += var12;
							arg1 += Pix2D.width2d;
						}
					}
				}
			}
		} else if (arg2 < Pix2D.boundBottom) {
			if (arg0 > Pix2D.boundBottom) {
				arg0 = Pix2D.boundBottom;
			}
			if (arg1 > Pix2D.boundBottom) {
				arg1 = Pix2D.boundBottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				arg7 = arg8 <<= 0xF;
				if (arg2 < 0) {
					arg4 -= var11 * arg2;
					arg5 -= var13 * arg2;
					arg7 -= var12 * arg2;
					arg8 -= var14 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				arg6 <<= 0xF;
				if (arg0 < 0) {
					arg3 -= var9 * arg0;
					arg6 -= var10 * arg0;
					arg0 = 0;
				}
				if (var11 < var13) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								gouraudRaster(Pix2D.data, arg2, 0, 0, arg4 >> 16, arg3 >> 16, arg7 >> 7, arg6 >> 7);
								arg4 += var11;
								arg3 += var9;
								arg7 += var12;
								arg6 += var10;
								arg2 += Pix2D.width2d;
							}
						}
						gouraudRaster(Pix2D.data, arg2, 0, 0, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
						arg4 += var11;
						arg5 += var13;
						arg7 += var12;
						arg8 += var14;
						arg2 += Pix2D.width2d;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								gouraudRaster(Pix2D.data, arg2, 0, 0, arg3 >> 16, arg4 >> 16, arg6 >> 7, arg7 >> 7);
								arg4 += var11;
								arg3 += var9;
								arg7 += var12;
								arg6 += var10;
								arg2 += Pix2D.width2d;
							}
						}
						gouraudRaster(Pix2D.data, arg2, 0, 0, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
						arg4 += var11;
						arg5 += var13;
						arg7 += var12;
						arg8 += var14;
						arg2 += Pix2D.width2d;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				arg6 = arg8 <<= 0xF;
				if (arg2 < 0) {
					arg3 -= var11 * arg2;
					arg5 -= var13 * arg2;
					arg6 -= var12 * arg2;
					arg8 -= var14 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				arg7 <<= 0xF;
				if (arg1 < 0) {
					arg4 -= var9 * arg1;
					arg7 -= var10 * arg1;
					arg1 = 0;
				}
				if (var11 < var13) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								gouraudRaster(Pix2D.data, arg2, 0, 0, arg4 >> 16, arg5 >> 16, arg7 >> 7, arg8 >> 7);
								arg4 += var9;
								arg5 += var13;
								arg7 += var10;
								arg8 += var14;
								arg2 += Pix2D.width2d;
							}
						}
						gouraudRaster(Pix2D.data, arg2, 0, 0, arg3 >> 16, arg5 >> 16, arg6 >> 7, arg8 >> 7);
						arg3 += var11;
						arg5 += var13;
						arg6 += var12;
						arg8 += var14;
						arg2 += Pix2D.width2d;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								gouraudRaster(Pix2D.data, arg2, 0, 0, arg5 >> 16, arg4 >> 16, arg8 >> 7, arg7 >> 7);
								arg4 += var9;
								arg5 += var13;
								arg7 += var10;
								arg8 += var14;
								arg2 += Pix2D.width2d;
							}
						}
						gouraudRaster(Pix2D.data, arg2, 0, 0, arg5 >> 16, arg3 >> 16, arg8 >> 7, arg6 >> 7);
						arg3 += var11;
						arg5 += var13;
						arg6 += var12;
						arg8 += var14;
						arg2 += Pix2D.width2d;
					}
				}
			}
		}
	}

	@ObfuscatedName("gb.a([IIIIIIII)V")
	public static final void gouraudRaster(int[] arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7) {
		int var8;
		int var9;
		int var10;
		int var11;
		int var17;
		if (jagged) {
			if (hclip) {
				if (arg5 - arg4 > 3) {
					var8 = (arg7 - arg6) / (arg5 - arg4);
				} else {
					var8 = 0;
				}
				if (arg5 > Pix2D.safeWidth) {
					arg5 = Pix2D.safeWidth;
				}
				if (arg4 < 0) {
					arg6 -= arg4 * var8;
					arg4 = 0;
				}
				if (arg4 >= arg5) {
					return;
				}
				arg1 += arg4;
				arg3 = arg5 - arg4 >> 2;
				var8 <<= 0x2;
			} else if (arg4 < arg5) {
				arg1 += arg4;
				arg3 = arg5 - arg4 >> 2;
				if (arg3 > 0) {
					var8 = (arg7 - arg6) * divTable[arg3] >> 15;
				} else {
					var8 = 0;
				}
			} else {
				return;
			}
			int var13;
			if (trans == 0) {
				while (true) {
					arg3--;
					if (arg3 < 0) {
						var17 = arg5 - arg4 & 0x3;
						if (var17 > 0) {
							var11 = colourTable[arg6 >> 8];
							do {
								arg0[arg1++] = var11;
								var17--;
							} while (var17 > 0);
							return;
						}
						break;
					}
					var11 = colourTable[arg6 >> 8];
					arg6 += var8;
					var13 = arg1 + 1;
					arg0[arg1] = var11;
					int var14 = var13 + 1;
					arg0[var13] = var11;
					int var15 = var14 + 1;
					arg0[var14] = var11;
					arg1 = var15 + 1;
					arg0[var15] = var11;
				}
			} else {
				var9 = trans;
				var10 = 256 - trans;
				while (true) {
					arg3--;
					if (arg3 < 0) {
						var17 = arg5 - arg4 & 0x3;
						if (var17 > 0) {
							var11 = colourTable[arg6 >> 8];
							var11 = ((var11 & 0xFF00FF) * var10 >> 8 & 0xFF00FF) + ((var11 & 0xFF00) * var10 >> 8 & 0xFF00);
							do {
								arg0[arg1++] = var11 + ((arg0[arg1] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * var9 >> 8 & 0xFF00);
								var17--;
							} while (var17 > 0);
						}
						break;
					}
					var11 = colourTable[arg6 >> 8];
					arg6 += var8;
					var11 = ((var11 & 0xFF00FF) * var10 >> 8 & 0xFF00FF) + ((var11 & 0xFF00) * var10 >> 8 & 0xFF00);
					var13 = arg1 + 1;
					arg0[arg1] = var11 + ((arg0[var13] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[var13] & 0xFF00) * var9 >> 8 & 0xFF00);
					arg0[var13++] = var11 + ((arg0[var13] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[var13] & 0xFF00) * var9 >> 8 & 0xFF00);
					arg0[var13++] = var11 + ((arg0[var13] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[var13] & 0xFF00) * var9 >> 8 & 0xFF00);
					arg1 = var13 + 1;
					arg0[var13] = var11 + ((arg0[arg1] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * var9 >> 8 & 0xFF00);
				}
			}
		} else if (arg4 < arg5) {
			var8 = (arg7 - arg6) / (arg5 - arg4);
			if (hclip) {
				if (arg5 > Pix2D.safeWidth) {
					arg5 = Pix2D.safeWidth;
				}
				if (arg4 < 0) {
					arg6 -= arg4 * var8;
					arg4 = 0;
				}
				if (arg4 >= arg5) {
					return;
				}
			}
			int var16 = arg1 + arg4;
			var17 = arg5 - arg4;
			if (trans == 0) {
				do {
					arg0[var16++] = colourTable[arg6 >> 8];
					arg6 += var8;
					var17--;
				} while (var17 > 0);
			} else {
				var9 = trans;
				var10 = 256 - trans;
				do {
					var11 = colourTable[arg6 >> 8];
					arg6 += var8;
					int var12 = ((var11 & 0xFF00FF) * var10 >> 8 & 0xFF00FF) + ((var11 & 0xFF00) * var10 >> 8 & 0xFF00);
					arg0[var16++] = var12 + ((arg0[var16] & 0xFF00FF) * var9 >> 8 & 0xFF00FF) + ((arg0[var16] & 0xFF00) * var9 >> 8 & 0xFF00);
					var17--;
				} while (var17 > 0);
			}
		}
	}

	@ObfuscatedName("gb.a(IIIIIII)V")
	public static final void flatTriangle(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
		int var7 = 0;
		if (arg1 != arg0) {
			var7 = (arg4 - arg3 << 16) / (arg1 - arg0);
		}
		int var8 = 0;
		if (arg2 != arg1) {
			var8 = (arg5 - arg4 << 16) / (arg2 - arg1);
		}
		int var9 = 0;
		if (arg2 != arg0) {
			var9 = (arg3 - arg5 << 16) / (arg0 - arg2);
		}
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Pix2D.boundBottom) {
				if (arg1 > Pix2D.boundBottom) {
					arg1 = Pix2D.boundBottom;
				}
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					if (arg0 < 0) {
						arg5 -= var9 * arg0;
						arg3 -= var7 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					if (arg1 < 0) {
						arg4 -= var8 * arg1;
						arg1 = 0;
					}
					if (arg0 != arg1 && var9 < var7 || arg0 == arg1 && var9 > var8) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg0, arg6, 0, arg5 >> 16, arg4 >> 16);
									arg5 += var9;
									arg4 += var8;
									arg0 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg0, arg6, 0, arg5 >> 16, arg3 >> 16);
							arg5 += var9;
							arg3 += var7;
							arg0 += Pix2D.width2d;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg0, arg6, 0, arg4 >> 16, arg5 >> 16);
									arg5 += var9;
									arg4 += var8;
									arg0 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg0, arg6, 0, arg3 >> 16, arg5 >> 16);
							arg5 += var9;
							arg3 += var7;
							arg0 += Pix2D.width2d;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					if (arg0 < 0) {
						arg4 -= var9 * arg0;
						arg3 -= var7 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= var8 * arg2;
						arg2 = 0;
					}
					if (arg0 != arg2 && var9 < var7 || arg0 == arg2 && var8 > var7) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg0, arg6, 0, arg5 >> 16, arg3 >> 16);
									arg5 += var8;
									arg3 += var7;
									arg0 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg0, arg6, 0, arg4 >> 16, arg3 >> 16);
							arg4 += var9;
							arg3 += var7;
							arg0 += Pix2D.width2d;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg0, arg6, 0, arg3 >> 16, arg5 >> 16);
									arg5 += var8;
									arg3 += var7;
									arg0 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg0, arg6, 0, arg3 >> 16, arg4 >> 16);
							arg4 += var9;
							arg3 += var7;
							arg0 += Pix2D.width2d;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Pix2D.boundBottom) {
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg0 > Pix2D.boundBottom) {
					arg0 = Pix2D.boundBottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					if (arg1 < 0) {
						arg3 -= var7 * arg1;
						arg4 -= var8 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= var9 * arg2;
						arg2 = 0;
					}
					if (arg1 != arg2 && var7 < var8 || arg1 == arg2 && var7 > var9) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg1, arg6, 0, arg3 >> 16, arg5 >> 16);
									arg3 += var7;
									arg5 += var9;
									arg1 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg1, arg6, 0, arg3 >> 16, arg4 >> 16);
							arg3 += var7;
							arg4 += var8;
							arg1 += Pix2D.width2d;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg1, arg6, 0, arg5 >> 16, arg3 >> 16);
									arg3 += var7;
									arg5 += var9;
									arg1 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg1, arg6, 0, arg4 >> 16, arg3 >> 16);
							arg3 += var7;
							arg4 += var8;
							arg1 += Pix2D.width2d;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					if (arg1 < 0) {
						arg5 -= var7 * arg1;
						arg4 -= var8 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					if (arg0 < 0) {
						arg3 -= var9 * arg0;
						arg0 = 0;
					}
					if (var7 < var8) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg1, arg6, 0, arg3 >> 16, arg4 >> 16);
									arg3 += var9;
									arg4 += var8;
									arg1 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg1, arg6, 0, arg5 >> 16, arg4 >> 16);
							arg5 += var7;
							arg4 += var8;
							arg1 += Pix2D.width2d;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									flatRaster(Pix2D.data, arg1, arg6, 0, arg4 >> 16, arg3 >> 16);
									arg3 += var9;
									arg4 += var8;
									arg1 += Pix2D.width2d;
								}
							}
							flatRaster(Pix2D.data, arg1, arg6, 0, arg4 >> 16, arg5 >> 16);
							arg5 += var7;
							arg4 += var8;
							arg1 += Pix2D.width2d;
						}
					}
				}
			}
		} else if (arg2 < Pix2D.boundBottom) {
			if (arg0 > Pix2D.boundBottom) {
				arg0 = Pix2D.boundBottom;
			}
			if (arg1 > Pix2D.boundBottom) {
				arg1 = Pix2D.boundBottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg4 -= var8 * arg2;
					arg5 -= var9 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				if (arg0 < 0) {
					arg3 -= var7 * arg0;
					arg0 = 0;
				}
				if (var8 < var9) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								flatRaster(Pix2D.data, arg2, arg6, 0, arg4 >> 16, arg3 >> 16);
								arg4 += var8;
								arg3 += var7;
								arg2 += Pix2D.width2d;
							}
						}
						flatRaster(Pix2D.data, arg2, arg6, 0, arg4 >> 16, arg5 >> 16);
						arg4 += var8;
						arg5 += var9;
						arg2 += Pix2D.width2d;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								flatRaster(Pix2D.data, arg2, arg6, 0, arg3 >> 16, arg4 >> 16);
								arg4 += var8;
								arg3 += var7;
								arg2 += Pix2D.width2d;
							}
						}
						flatRaster(Pix2D.data, arg2, arg6, 0, arg5 >> 16, arg4 >> 16);
						arg4 += var8;
						arg5 += var9;
						arg2 += Pix2D.width2d;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				if (arg2 < 0) {
					arg3 -= var8 * arg2;
					arg5 -= var9 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				if (arg1 < 0) {
					arg4 -= var7 * arg1;
					arg1 = 0;
				}
				if (var8 < var9) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								flatRaster(Pix2D.data, arg2, arg6, 0, arg4 >> 16, arg5 >> 16);
								arg4 += var7;
								arg5 += var9;
								arg2 += Pix2D.width2d;
							}
						}
						flatRaster(Pix2D.data, arg2, arg6, 0, arg3 >> 16, arg5 >> 16);
						arg3 += var8;
						arg5 += var9;
						arg2 += Pix2D.width2d;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								flatRaster(Pix2D.data, arg2, arg6, 0, arg5 >> 16, arg4 >> 16);
								arg4 += var7;
								arg5 += var9;
								arg2 += Pix2D.width2d;
							}
						}
						flatRaster(Pix2D.data, arg2, arg6, 0, arg5 >> 16, arg3 >> 16);
						arg3 += var8;
						arg5 += var9;
						arg2 += Pix2D.width2d;
					}
				}
			}
		}
	}

	@ObfuscatedName("gb.a([IIIIII)V")
	public static final void flatRaster(int[] arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		if (hclip) {
			if (arg5 > Pix2D.safeWidth) {
				arg5 = Pix2D.safeWidth;
			}
			if (arg4 < 0) {
				arg4 = 0;
			}
		}
		if (arg4 >= arg5) {
			return;
		}
		arg1 += arg4;
		int var15 = arg5 - arg4 >> 2;
		int var8;
		if (trans == 0) {
			while (true) {
				var15--;
				if (var15 < 0) {
					var15 = arg5 - arg4 & 0x3;
					while (true) {
						var15--;
						if (var15 < 0) {
							return;
						}
						arg0[arg1++] = arg2;
					}
				}
				var8 = arg1 + 1;
				arg0[arg1] = arg2;
				arg0[var8++] = arg2;
				arg0[var8++] = arg2;
				arg1 = var8 + 1;
				arg0[var8] = arg2;
			}
		}
		int var6 = trans;
		int var7 = 256 - trans;
		int var10 = ((arg2 & 0xFF00FF) * var7 >> 8 & 0xFF00FF) + ((arg2 & 0xFF00) * var7 >> 8 & 0xFF00);
		while (true) {
			var15--;
			if (var15 < 0) {
				var15 = arg5 - arg4 & 0x3;
				while (true) {
					var15--;
					if (var15 < 0) {
						return;
					}
					arg0[arg1++] = var10 + ((arg0[arg1] & 0xFF00FF) * var6 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * var6 >> 8 & 0xFF00);
				}
			}
			var8 = arg1 + 1;
			arg0[arg1] = var10 + ((arg0[var8] & 0xFF00FF) * var6 >> 8 & 0xFF00FF) + ((arg0[var8] & 0xFF00) * var6 >> 8 & 0xFF00);
			int var9 = var8 + 1;
			arg0[var8] = var10 + ((arg0[var9] & 0xFF00FF) * var6 >> 8 & 0xFF00FF) + ((arg0[var9] & 0xFF00) * var6 >> 8 & 0xFF00);
			int var11 = var9 + 1;
			arg0[var9] = var10 + ((arg0[var11] & 0xFF00FF) * var6 >> 8 & 0xFF00FF) + ((arg0[var11] & 0xFF00) * var6 >> 8 & 0xFF00);
			arg1 = var11 + 1;
			arg0[var11] = var10 + ((arg0[arg1] & 0xFF00FF) * var6 >> 8 & 0xFF00FF) + ((arg0[arg1] & 0xFF00) * var6 >> 8 & 0xFF00);
		}
	}

	@ObfuscatedName("gb.a(IIIIIIIIIIIIIIIIIII)V")
	public static final void textureTriangle(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, int arg14, int arg15, int arg16, int arg17, int arg18) {
		int[] var19 = getTexels(arg18);
		opaque = !textureTranslucent[arg18];
		int var36 = arg9 - arg10;
		int var38 = arg12 - arg13;
		int var40 = arg15 - arg16;
		int var37 = arg11 - arg9;
		int var39 = arg14 - arg12;
		int var41 = arg17 - arg15;
		int var20 = var37 * arg12 - var39 * arg9 << 14;
		int var21 = var39 * arg15 - var41 * arg12 << 8;
		int var22 = var41 * arg9 - var37 * arg15 << 5;
		int var23 = var36 * arg12 - var38 * arg9 << 14;
		int var24 = var38 * arg15 - var40 * arg12 << 8;
		int var25 = var40 * arg9 - var36 * arg15 << 5;
		int var26 = var38 * var37 - var36 * var39 << 14;
		int var27 = var40 * var39 - var38 * var41 << 8;
		int var28 = var36 * var41 - var40 * var37 << 5;
		int var29 = 0;
		int var30 = 0;
		if (arg1 != arg0) {
			var29 = (arg4 - arg3 << 16) / (arg1 - arg0);
			var30 = (arg7 - arg6 << 16) / (arg1 - arg0);
		}
		int var31 = 0;
		int var32 = 0;
		if (arg2 != arg1) {
			var31 = (arg5 - arg4 << 16) / (arg2 - arg1);
			var32 = (arg8 - arg7 << 16) / (arg2 - arg1);
		}
		int var33 = 0;
		int var34 = 0;
		if (arg2 != arg0) {
			var33 = (arg3 - arg5 << 16) / (arg0 - arg2);
			var34 = (arg6 - arg8 << 16) / (arg0 - arg2);
		}
		int var35;
		if (arg0 <= arg1 && arg0 <= arg2) {
			if (arg0 < Pix2D.boundBottom) {
				if (arg1 > Pix2D.boundBottom) {
					arg1 = Pix2D.boundBottom;
				}
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg1 < arg2) {
					arg5 = arg3 <<= 0x10;
					arg8 = arg6 <<= 0x10;
					if (arg0 < 0) {
						arg5 -= var33 * arg0;
						arg3 -= var29 * arg0;
						arg8 -= var34 * arg0;
						arg6 -= var30 * arg0;
						arg0 = 0;
					}
					arg4 <<= 0x10;
					arg7 <<= 0x10;
					if (arg1 < 0) {
						arg4 -= var31 * arg1;
						arg7 -= var32 * arg1;
						arg1 = 0;
					}
					var35 = arg0 - centerH3D;
					var20 += var22 * var35;
					var23 += var25 * var35;
					var26 += var28 * var35;
					if (arg0 != arg1 && var33 < var29 || arg0 == arg1 && var33 > var31) {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg0, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
									arg5 += var33;
									arg4 += var31;
									arg8 += var34;
									arg7 += var32;
									arg0 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
							arg5 += var33;
							arg3 += var29;
							arg8 += var34;
							arg6 += var30;
							arg0 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					} else {
						arg2 -= arg1;
						arg1 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg1--;
							if (arg1 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg0, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
									arg5 += var33;
									arg4 += var31;
									arg8 += var34;
									arg7 += var32;
									arg0 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
							arg5 += var33;
							arg3 += var29;
							arg8 += var34;
							arg6 += var30;
							arg0 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					}
				} else {
					arg4 = arg3 <<= 0x10;
					arg7 = arg6 <<= 0x10;
					if (arg0 < 0) {
						arg4 -= var33 * arg0;
						arg3 -= var29 * arg0;
						arg7 -= var34 * arg0;
						arg6 -= var30 * arg0;
						arg0 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= var31 * arg2;
						arg8 -= var32 * arg2;
						arg2 = 0;
					}
					var35 = arg0 - centerH3D;
					var20 += var22 * var35;
					var23 += var25 * var35;
					var26 += var28 * var35;
					if ((arg0 == arg2 || var33 >= var29) && (arg0 != arg2 || var31 <= var29)) {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg0, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
									arg5 += var31;
									arg3 += var29;
									arg8 += var32;
									arg6 += var30;
									arg0 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg0, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
							arg4 += var33;
							arg3 += var29;
							arg7 += var34;
							arg6 += var30;
							arg0 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					} else {
						arg1 -= arg2;
						arg2 -= arg0;
						arg0 = lineOffset[arg0];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg1--;
									if (arg1 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg0, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
									arg5 += var31;
									arg3 += var29;
									arg8 += var32;
									arg6 += var30;
									arg0 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg0, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
							arg4 += var33;
							arg3 += var29;
							arg7 += var34;
							arg6 += var30;
							arg0 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					}
				}
			}
		} else if (arg1 <= arg2) {
			if (arg1 < Pix2D.boundBottom) {
				if (arg2 > Pix2D.boundBottom) {
					arg2 = Pix2D.boundBottom;
				}
				if (arg0 > Pix2D.boundBottom) {
					arg0 = Pix2D.boundBottom;
				}
				if (arg2 < arg0) {
					arg3 = arg4 <<= 0x10;
					arg6 = arg7 <<= 0x10;
					if (arg1 < 0) {
						arg3 -= var29 * arg1;
						arg4 -= var31 * arg1;
						arg6 -= var30 * arg1;
						arg7 -= var32 * arg1;
						arg1 = 0;
					}
					arg5 <<= 0x10;
					arg8 <<= 0x10;
					if (arg2 < 0) {
						arg5 -= var33 * arg2;
						arg8 -= var34 * arg2;
						arg2 = 0;
					}
					var35 = arg1 - centerH3D;
					var20 += var22 * var35;
					var23 += var25 * var35;
					var26 += var28 * var35;
					if (arg1 != arg2 && var29 < var31 || arg1 == arg2 && var29 > var33) {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg1, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
									arg3 += var29;
									arg5 += var33;
									arg6 += var30;
									arg8 += var34;
									arg1 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
							arg3 += var29;
							arg4 += var31;
							arg6 += var30;
							arg7 += var32;
							arg1 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					} else {
						arg0 -= arg2;
						arg2 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg2--;
							if (arg2 < 0) {
								while (true) {
									arg0--;
									if (arg0 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg1, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
									arg3 += var29;
									arg5 += var33;
									arg6 += var30;
									arg8 += var34;
									arg1 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
							arg3 += var29;
							arg4 += var31;
							arg6 += var30;
							arg7 += var32;
							arg1 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					}
				} else {
					arg5 = arg4 <<= 0x10;
					arg8 = arg7 <<= 0x10;
					if (arg1 < 0) {
						arg5 -= var29 * arg1;
						arg4 -= var31 * arg1;
						arg8 -= var30 * arg1;
						arg7 -= var32 * arg1;
						arg1 = 0;
					}
					arg3 <<= 0x10;
					arg6 <<= 0x10;
					if (arg0 < 0) {
						arg3 -= var33 * arg0;
						arg6 -= var34 * arg0;
						arg0 = 0;
					}
					var35 = arg1 - centerH3D;
					var20 += var22 * var35;
					var23 += var25 * var35;
					var26 += var28 * var35;
					if (var29 < var31) {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg1, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
									arg3 += var33;
									arg4 += var31;
									arg6 += var34;
									arg7 += var32;
									arg1 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg1, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
							arg5 += var29;
							arg4 += var31;
							arg8 += var30;
							arg7 += var32;
							arg1 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					} else {
						arg2 -= arg0;
						arg0 -= arg1;
						arg1 = lineOffset[arg1];
						while (true) {
							arg0--;
							if (arg0 < 0) {
								while (true) {
									arg2--;
									if (arg2 < 0) {
										return;
									}
									textureRaster(Pix2D.data, var19, 0, 0, arg1, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
									arg3 += var33;
									arg4 += var31;
									arg6 += var34;
									arg7 += var32;
									arg1 += Pix2D.width2d;
									var20 += var22;
									var23 += var25;
									var26 += var28;
								}
							}
							textureRaster(Pix2D.data, var19, 0, 0, arg1, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
							arg5 += var29;
							arg4 += var31;
							arg8 += var30;
							arg7 += var32;
							arg1 += Pix2D.width2d;
							var20 += var22;
							var23 += var25;
							var26 += var28;
						}
					}
				}
			}
		} else if (arg2 < Pix2D.boundBottom) {
			if (arg0 > Pix2D.boundBottom) {
				arg0 = Pix2D.boundBottom;
			}
			if (arg1 > Pix2D.boundBottom) {
				arg1 = Pix2D.boundBottom;
			}
			if (arg0 < arg1) {
				arg4 = arg5 <<= 0x10;
				arg7 = arg8 <<= 0x10;
				if (arg2 < 0) {
					arg4 -= var31 * arg2;
					arg5 -= var33 * arg2;
					arg7 -= var32 * arg2;
					arg8 -= var34 * arg2;
					arg2 = 0;
				}
				arg3 <<= 0x10;
				arg6 <<= 0x10;
				if (arg0 < 0) {
					arg3 -= var29 * arg0;
					arg6 -= var30 * arg0;
					arg0 = 0;
				}
				var35 = arg2 - centerH3D;
				var20 += var22 * var35;
				var23 += var25 * var35;
				var26 += var28 * var35;
				if (var31 < var33) {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								textureRaster(Pix2D.data, var19, 0, 0, arg2, arg4 >> 16, arg3 >> 16, arg7 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
								arg4 += var31;
								arg3 += var29;
								arg7 += var32;
								arg6 += var30;
								arg2 += Pix2D.width2d;
								var20 += var22;
								var23 += var25;
								var26 += var28;
							}
						}
						textureRaster(Pix2D.data, var19, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
						arg4 += var31;
						arg5 += var33;
						arg7 += var32;
						arg8 += var34;
						arg2 += Pix2D.width2d;
						var20 += var22;
						var23 += var25;
						var26 += var28;
					}
				} else {
					arg1 -= arg0;
					arg0 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg0--;
						if (arg0 < 0) {
							while (true) {
								arg1--;
								if (arg1 < 0) {
									return;
								}
								textureRaster(Pix2D.data, var19, 0, 0, arg2, arg3 >> 16, arg4 >> 16, arg6 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
								arg4 += var31;
								arg3 += var29;
								arg7 += var32;
								arg6 += var30;
								arg2 += Pix2D.width2d;
								var20 += var22;
								var23 += var25;
								var26 += var28;
							}
						}
						textureRaster(Pix2D.data, var19, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
						arg4 += var31;
						arg5 += var33;
						arg7 += var32;
						arg8 += var34;
						arg2 += Pix2D.width2d;
						var20 += var22;
						var23 += var25;
						var26 += var28;
					}
				}
			} else {
				arg3 = arg5 <<= 0x10;
				arg6 = arg8 <<= 0x10;
				if (arg2 < 0) {
					arg3 -= var31 * arg2;
					arg5 -= var33 * arg2;
					arg6 -= var32 * arg2;
					arg8 -= var34 * arg2;
					arg2 = 0;
				}
				arg4 <<= 0x10;
				arg7 <<= 0x10;
				if (arg1 < 0) {
					arg4 -= var29 * arg1;
					arg7 -= var30 * arg1;
					arg1 = 0;
				}
				var35 = arg2 - centerH3D;
				var20 += var22 * var35;
				var23 += var25 * var35;
				var26 += var28 * var35;
				if (var31 < var33) {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								textureRaster(Pix2D.data, var19, 0, 0, arg2, arg4 >> 16, arg5 >> 16, arg7 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
								arg4 += var29;
								arg5 += var33;
								arg7 += var30;
								arg8 += var34;
								arg2 += Pix2D.width2d;
								var20 += var22;
								var23 += var25;
								var26 += var28;
							}
						}
						textureRaster(Pix2D.data, var19, 0, 0, arg2, arg3 >> 16, arg5 >> 16, arg6 >> 8, arg8 >> 8, var20, var23, var26, var21, var24, var27);
						arg3 += var31;
						arg5 += var33;
						arg6 += var32;
						arg8 += var34;
						arg2 += Pix2D.width2d;
						var20 += var22;
						var23 += var25;
						var26 += var28;
					}
				} else {
					arg0 -= arg1;
					arg1 -= arg2;
					arg2 = lineOffset[arg2];
					while (true) {
						arg1--;
						if (arg1 < 0) {
							while (true) {
								arg0--;
								if (arg0 < 0) {
									return;
								}
								textureRaster(Pix2D.data, var19, 0, 0, arg2, arg5 >> 16, arg4 >> 16, arg8 >> 8, arg7 >> 8, var20, var23, var26, var21, var24, var27);
								arg4 += var29;
								arg5 += var33;
								arg7 += var30;
								arg8 += var34;
								arg2 += Pix2D.width2d;
								var20 += var22;
								var23 += var25;
								var26 += var28;
							}
						}
						textureRaster(Pix2D.data, var19, 0, 0, arg2, arg5 >> 16, arg3 >> 16, arg8 >> 8, arg6 >> 8, var20, var23, var26, var21, var24, var27);
						arg3 += var31;
						arg5 += var33;
						arg6 += var32;
						arg8 += var34;
						arg2 += Pix2D.width2d;
						var20 += var22;
						var23 += var25;
						var26 += var28;
					}
				}
			}
		}
	}

	@ObfuscatedName("gb.a([I[IIIIIIIIIIIIII)V")
	public static final void textureRaster(int[] arg0, int[] arg1, int arg2, int arg3, int arg4, int arg5, int arg6, int arg7, int arg8, int arg9, int arg10, int arg11, int arg12, int arg13, int arg14) {
		if (arg5 >= arg6) {
			return;
		}
		int var15;
		int var16;
		if (hclip) {
			var15 = (arg8 - arg7) / (arg6 - arg5);
			if (arg6 > Pix2D.safeWidth) {
				arg6 = Pix2D.safeWidth;
			}
			if (arg5 < 0) {
				arg7 -= arg5 * var15;
				arg5 = 0;
			}
			if (arg5 >= arg6) {
				return;
			}
			var16 = arg6 - arg5 >> 3;
			var15 <<= 0xC;
			arg7 <<= 0x9;
		} else {
			if (arg6 - arg5 > 7) {
				var16 = arg6 - arg5 >> 3;
				var15 = (arg8 - arg7) * divTable[var16] >> 6;
			} else {
				var16 = 0;
				var15 = 0;
			}
			arg7 <<= 0x9;
		}
		arg4 += arg5;
		int var17;
		int var18;
		int var19;
		int var20;
		int var21;
		int var22;
		int var23;
		int var25;
		int var32;
		int var33;
		int var34;
		if (lowDetail) {
			var17 = 0;
			var18 = 0;
			var20 = arg5 - centerW3D;
			var32 = arg9 + (arg12 >> 3) * var20;
			var33 = arg10 + (arg13 >> 3) * var20;
			var34 = arg11 + (arg14 >> 3) * var20;
			var19 = var34 >> 12;
			if (var19 != 0) {
				arg2 = var32 / var19;
				arg3 = var33 / var19;
				if (arg2 < 0) {
					arg2 = 0;
				} else if (arg2 > 4032) {
					arg2 = 4032;
				}
			}
			arg9 = var32 + arg12;
			arg10 = var33 + arg13;
			arg11 = var34 + arg14;
			var19 = arg11 >> 12;
			if (var19 != 0) {
				var17 = arg9 / var19;
				var18 = arg10 / var19;
				if (var17 < 7) {
					var17 = 7;
				} else if (var17 > 4032) {
					var17 = 4032;
				}
			}
			var21 = var17 - arg2 >> 3;
			var22 = var18 - arg3 >> 3;
			arg2 += arg7 >> 3 & 0xC0000;
			var23 = arg7 >> 23;
			if (opaque) {
				while (var16-- > 0) {
					var25 = arg4 + 1;
					arg0[arg4] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg0[var25++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
					arg4 = var25 + 1;
					arg0[var25] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 = var17;
					arg3 = var18;
					arg9 += arg12;
					arg10 += arg13;
					arg11 += arg14;
					var19 = arg11 >> 12;
					if (var19 != 0) {
						var17 = arg9 / var19;
						var18 = arg10 / var19;
						if (var17 < 7) {
							var17 = 7;
						} else if (var17 > 4032) {
							var17 = 4032;
						}
					}
					var21 = var17 - arg2 >> 3;
					var22 = var18 - arg3 >> 3;
					arg7 += var15;
					arg2 += arg7 >> 3 & 0xC0000;
					var23 = arg7 >> 23;
				}
				var16 = arg6 - arg5 & 0x7;
				while (var16-- > 0) {
					arg0[arg4++] = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23;
					arg2 += var21;
					arg3 += var22;
				}
			} else {
				while (var16-- > 0) {
					int var43;
					if ((var43 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[arg4] = var43;
					}
					var25 = arg4 + 1;
					arg2 += var21;
					arg3 += var22;
					int var44;
					if ((var44 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var44;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var45;
					if ((var45 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var45;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var46;
					if ((var46 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var46;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var47;
					if ((var47 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var47;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var48;
					if ((var48 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var48;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var49;
					if ((var49 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var49;
					}
					var25++;
					arg2 += var21;
					arg3 += var22;
					int var50;
					if ((var50 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[var25] = var50;
					}
					arg4 = var25 + 1;
					arg2 = var17;
					arg3 = var18;
					arg9 += arg12;
					arg10 += arg13;
					arg11 += arg14;
					var19 = arg11 >> 12;
					if (var19 != 0) {
						var17 = arg9 / var19;
						var18 = arg10 / var19;
						if (var17 < 7) {
							var17 = 7;
						} else if (var17 > 4032) {
							var17 = 4032;
						}
					}
					var21 = var17 - arg2 >> 3;
					var22 = var18 - arg3 >> 3;
					arg7 += var15;
					arg2 += arg7 >> 3 & 0xC0000;
					var23 = arg7 >> 23;
				}
				var16 = arg6 - arg5 & 0x7;
				while (var16-- > 0) {
					int var51;
					if ((var51 = arg1[(arg3 & 0xFC0) + (arg2 >> 6)] >>> var23) != 0) {
						arg0[arg4] = var51;
					}
					arg4++;
					arg2 += var21;
					arg3 += var22;
				}
			}
			return;
		}
		var17 = 0;
		var18 = 0;
		var20 = arg5 - centerW3D;
		var32 = arg9 + (arg12 >> 3) * var20;
		var33 = arg10 + (arg13 >> 3) * var20;
		var34 = arg11 + (arg14 >> 3) * var20;
		var19 = var34 >> 14;
		if (var19 != 0) {
			arg2 = var32 / var19;
			arg3 = var33 / var19;
			if (arg2 < 0) {
				arg2 = 0;
			} else if (arg2 > 16256) {
				arg2 = 16256;
			}
		}
		arg9 = var32 + arg12;
		arg10 = var33 + arg13;
		arg11 = var34 + arg14;
		var19 = arg11 >> 14;
		if (var19 != 0) {
			var17 = arg9 / var19;
			var18 = arg10 / var19;
			if (var17 < 7) {
				var17 = 7;
			} else if (var17 > 16256) {
				var17 = 16256;
			}
		}
		var21 = var17 - arg2 >> 3;
		var22 = var18 - arg3 >> 3;
		arg2 += arg7 & 0x600000;
		var23 = arg7 >> 23;
		if (opaque) {
			while (var16-- > 0) {
				var25 = arg4 + 1;
				arg0[arg4] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var26 = var25 + 1;
				arg0[var25] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var27 = var26 + 1;
				arg0[var26] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var28 = var27 + 1;
				arg0[var27] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var29 = var28 + 1;
				arg0[var28] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var30 = var29 + 1;
				arg0[var29] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				int var31 = var30 + 1;
				arg0[var30] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
				arg4 = var31 + 1;
				arg0[var31] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 = var17;
				arg3 = var18;
				arg9 += arg12;
				arg10 += arg13;
				arg11 += arg14;
				var19 = arg11 >> 14;
				if (var19 != 0) {
					var17 = arg9 / var19;
					var18 = arg10 / var19;
					if (var17 < 7) {
						var17 = 7;
					} else if (var17 > 16256) {
						var17 = 16256;
					}
				}
				var21 = var17 - arg2 >> 3;
				var22 = var18 - arg3 >> 3;
				arg7 += var15;
				arg2 += arg7 & 0x600000;
				var23 = arg7 >> 23;
			}
			var16 = arg6 - arg5 & 0x7;
			while (var16-- > 0) {
				arg0[arg4++] = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23;
				arg2 += var21;
				arg3 += var22;
			}
			return;
		}
		while (var16-- > 0) {
			int var24;
			if ((var24 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[arg4] = var24;
			}
			var25 = arg4 + 1;
			arg2 += var21;
			arg3 += var22;
			int var35;
			if ((var35 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var35;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var36;
			if ((var36 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var36;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var37;
			if ((var37 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var37;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var38;
			if ((var38 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var38;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var39;
			if ((var39 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var39;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var40;
			if ((var40 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var40;
			}
			var25++;
			arg2 += var21;
			arg3 += var22;
			int var41;
			if ((var41 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[var25] = var41;
			}
			arg4 = var25 + 1;
			arg2 = var17;
			arg3 = var18;
			arg9 += arg12;
			arg10 += arg13;
			arg11 += arg14;
			var19 = arg11 >> 14;
			if (var19 != 0) {
				var17 = arg9 / var19;
				var18 = arg10 / var19;
				if (var17 < 7) {
					var17 = 7;
				} else if (var17 > 16256) {
					var17 = 16256;
				}
			}
			var21 = var17 - arg2 >> 3;
			var22 = var18 - arg3 >> 3;
			arg7 += var15;
			arg2 += arg7 & 0x600000;
			var23 = arg7 >> 23;
		}
		var16 = arg6 - arg5 & 0x7;
		while (var16-- > 0) {
			int var42;
			if ((var42 = arg1[(arg3 & 0x3F80) + (arg2 >> 7)] >>> var23) != 0) {
				arg0[arg4] = var42;
			}
			arg4++;
			arg2 += var21;
			arg3 += var22;
		}
	}

	static {
		for (int var0 = 1; var0 < 512; var0++) {
			divTable[var0] = 32768 / var0;
		}
		for (int var1 = 1; var1 < 2048; var1++) {
			divTable2[var1] = 65536 / var1;
		}
		for (int var2 = 0; var2 < 2048; var2++) {
			sinTable[var2] = (int) (Math.sin((double) var2 * 0.0030679615D) * 65536.0D);
			cosTable[var2] = (int) (Math.cos((double) var2 * 0.0030679615D) * 65536.0D);
		}
		textures = new Pix8[50];
		textureTranslucent = new boolean[50];
		averageTextureRGB = new int[50];
		activeTexels = new int[50][];
		textureCycle = new int[50];
		colourTable = new int[65536];
		texturePalette = new int[50][];
	}
}
