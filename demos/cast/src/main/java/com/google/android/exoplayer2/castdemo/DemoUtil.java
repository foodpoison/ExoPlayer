/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.android.exoplayer2.castdemo;

import androidx.annotation.Nullable;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.util.MimeTypes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/** Utility methods and constants for the Cast demo application. */
/* package */ final class DemoUtil {

  /** Represents a media sample. */
  public static final class Sample {

    /** The URI of the media content. */
    public final String uri;
    /** The name of the sample. */
    public final String name;
    /** The mime type of the sample media content. */
    public final String mimeType;
    /** Data to configure DRM license acquisition. May be null if content is not DRM-protected. */
    @Nullable public final DrmConfiguration drmConfiguration;

    public Sample(String uri, String name, String mimeType) {
      this(uri, name, mimeType, /* drmConfiguration= */ null);
    }

    public Sample(
        String uri, String name, String mimeType, @Nullable DrmConfiguration drmConfiguration) {
      this.uri = uri;
      this.name = name;
      this.mimeType = mimeType;
      this.drmConfiguration = drmConfiguration;
    }

    @Override
    public String toString() {
      return name;
    }
  }

  /** Holds information required to play DRM-protected content. */
  public static final class DrmConfiguration {

    /** The {@link UUID} of the DRM scheme that protects the content. */
    public final UUID drmSchemeUuid;
    /**
     * The URI from which players should obtain DRM licenses. May be null if the license server URI
     * is provided as part of the media.
     */
    @Nullable public final String licenseServerUri;
    /** HTTP request headers to include the in DRM license requests. */
    public final Map<String, String> httpRequestHeaders;

    public DrmConfiguration(
        UUID drmSchemeUuid,
        @Nullable String licenseServerUri,
        Map<String, String> httpRequestHeaders) {
      this.drmSchemeUuid = drmSchemeUuid;
      this.licenseServerUri = licenseServerUri;
      this.httpRequestHeaders = httpRequestHeaders;
    }
  }

  public static final String MIME_TYPE_DASH = MimeTypes.APPLICATION_MPD;
  public static final String MIME_TYPE_HLS = MimeTypes.APPLICATION_M3U8;
  public static final String MIME_TYPE_SS = MimeTypes.APPLICATION_SS;
  public static final String MIME_TYPE_VIDEO_MP4 = MimeTypes.VIDEO_MP4;

  /** The list of samples available in the cast demo app. */
  public static final List<Sample> SAMPLES;

  static {
    // App samples.
    ArrayList<Sample> samples = new ArrayList<>();

    // Clear content.
    samples.add(
        new Sample(
            "https://storage.googleapis.com/wvmedia/clear/h264/tears/tears.mpd",
            "Clear DASH: Tears",
            MIME_TYPE_DASH));
    samples.add(
        new Sample(
            "https://html5demos.com/assets/dizzy.mp4", "Clear MP4: Dizzy", MIME_TYPE_VIDEO_MP4));

    SAMPLES = Collections.unmodifiableList(samples);
  }

  private DemoUtil() {}
}
