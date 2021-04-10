import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICodeValue, defaultValue } from 'app/shared/model/code-value.model';

export const ACTION_TYPES = {
  FETCH_CODEVALUE_LIST: 'codeValue/FETCH_CODEVALUE_LIST',
  FETCH_CODEVALUE: 'codeValue/FETCH_CODEVALUE',
  CREATE_CODEVALUE: 'codeValue/CREATE_CODEVALUE',
  UPDATE_CODEVALUE: 'codeValue/UPDATE_CODEVALUE',
  DELETE_CODEVALUE: 'codeValue/DELETE_CODEVALUE',
  RESET: 'codeValue/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICodeValue>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CodeValueState = Readonly<typeof initialState>;

// Reducer

export default (state: CodeValueState = initialState, action): CodeValueState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CODEVALUE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CODEVALUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CODEVALUE):
    case REQUEST(ACTION_TYPES.UPDATE_CODEVALUE):
    case REQUEST(ACTION_TYPES.DELETE_CODEVALUE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CODEVALUE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CODEVALUE):
    case FAILURE(ACTION_TYPES.CREATE_CODEVALUE):
    case FAILURE(ACTION_TYPES.UPDATE_CODEVALUE):
    case FAILURE(ACTION_TYPES.DELETE_CODEVALUE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CODEVALUE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CODEVALUE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CODEVALUE):
    case SUCCESS(ACTION_TYPES.UPDATE_CODEVALUE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CODEVALUE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/code-values';

// Actions

export const getEntities: ICrudGetAllAction<ICodeValue> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CODEVALUE_LIST,
  payload: axios.get<ICodeValue>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICodeValue> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CODEVALUE,
    payload: axios.get<ICodeValue>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICodeValue> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CODEVALUE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICodeValue> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CODEVALUE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICodeValue> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CODEVALUE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
