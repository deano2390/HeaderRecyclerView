/*
 * Copyright (C) 2015 Karumi.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.karumi.headerrecyclerview.sample;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karumi.headerrecyclerview.HeaderRecyclerViewAdapter;

import java.util.List;

/**
 * HeaderRecyclerViewAdapter extension created to show how to use the library using DragonBall
 * characters.
 */
public class DragonBallAdapter extends HeaderRecyclerViewAdapter<RecyclerView.ViewHolder> {

    private List<DragonBallCharacter> characters;

    public DragonBallAdapter(List<DragonBallCharacter> characters) {
        this.characters = characters;
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater(parent);
        View headerView = inflater.inflate(R.layout.row_dragon_ball_header, parent, false);
        return new HeaderViewHolder(headerView);
    }

    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = getLayoutInflater(parent);
        View characterView = inflater.inflate(R.layout.row_dragon_ball_character, parent, false);
        return new CharacterViewHolder(characterView);
    }


    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    protected void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
        DragonBallCharacter character = characters.get(position);
        CharacterViewHolder characterViewHolder = (CharacterViewHolder) holder;
        characterViewHolder.render(character);
    }

    @Override
    protected int getRealItemViewType(int position) {
        return R.layout.row_dragon_ball_character;
    }


    LayoutInflater getLayoutInflater(ViewGroup parent) {
        return LayoutInflater.from(parent.getContext());
    }


    @Override
    public int getRealItemCount() {
        if (characters != null) {
            return characters.size();
        }
        return 0;
    }

}
