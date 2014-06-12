/*
 * Copyright (c) 2014 Jonas Kalderstam.
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

package com.nononsenseapps.filepicker;

import android.os.Bundle;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.nononsenseapps.notepad.sync.orgsync.DropboxSyncHelper;

public class DropboxFilePickerActivity
        extends AbstractFilePickerActivity<DropboxAPI.Entry> {

    private DropboxAPI<AndroidAuthSession> dbApi;

    @Override
    public void onCreate(Bundle b) {// Make sure we are linked
        dbApi = DropboxSyncHelper.getDBApi(this);

        if (!dbApi.getSession().isLinked()) {
            finish();
        }

        super.onCreate(b);
    }

    @Override
    protected AbstractFilePickerFragment<DropboxAPI.Entry> getFragment(
            final String startPath, final int mode, final boolean allowMultiple,
            final boolean allowCreateDir) {
        DropboxFilePickerFragment fragment =
                new DropboxFilePickerFragment(dbApi);
        fragment.setArgs(startPath, mode, allowMultiple, allowCreateDir);
        return fragment;
    }
}
